package com.cos.photogramstart.service;


import com.cos.photogramstart.domain.subscribe.SubscribeRepository;
import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;  // qlrm : db에서 result 결과를 자바 클래스에 매핑해주는 라이브러리
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em; // Repository 는 EntityManager 를 구현해서 만들어져있는 구현체

    @Transactional(readOnly = true)
    public List<SubscribeDto> subscribeList(int principalId, int pageUserId) {

        // 스칼라 서브쿼리 활용 -> 쿼리 준비
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.username, u.profileImageUrl, ");
        sb.append("IF ((SELECT 1 FROM Subscribe s2 WHERE fromUserId = ? AND toUserId = u.id), 1, 0) subscribeState, ");
        sb.append("IF ((? = u.id), 1, 0) equalUserState ");
        sb.append("FROM `User` u INNER JOIN Subscribe s ");
        sb.append("ON u.id = s.toUserId ");
        sb.append("WHERE s.fromUserId = ?");  // 세미콜론 x

        // 1. 물음표 : principalId
        // 2. 물음표 : principalId
        // 3. 물음표 : pageUserId

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, principalId)
                .setParameter(2, principalId)
                .setParameter(3, pageUserId);

        // 쿼리 실행 (qlrm 라이브러리 필요 = Dto 에 DB 결과를 매핑하기 위해서)
        JpaResultMapper result = new JpaResultMapper();
        List<SubscribeDto> subscribeDtos = result.list(query, SubscribeDto.class);

        return subscribeDtos;
    }
    @Transactional
    public void subscribe(int fromUserId, int toUserId) {
        try {
            subscribeRepository.mSubscribe(fromUserId,  toUserId);
        } catch (Exception e) {
            throw new CustomApiException("이미 구독을 했습니다.");
        }
    }

    @Transactional
    public void unSubscribe(int fromUserId, int toUserId) {
        subscribeRepository.mUnSubscribe(fromUserId, toUserId);
    }


}
