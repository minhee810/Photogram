package com.cos.photogramstart.service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    public User update(int id, UserUpdateDto userUpdateDto) {
        // 1. 영속화 - 사용자 찾기 또는 예외 발생
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 사용자를 찾을 수 없습니다.")); // 사용자를 못찾았을 경우 예외 처리

        // 2. 정보 업데이트
        userEntity.setName(userUpdateDto.getName());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userUpdateDto.getPassword()));
        userEntity.setPhone(userUpdateDto.getPhone());
        userEntity.setBio(userUpdateDto.getBio());
        userEntity.setWebsite(userUpdateDto.getWebsite());
        userEntity.setGender(userUpdateDto.getGender());

        // 3. 변경된 정보가 영속성 컨텍스트에 의해 자동으로 DB에 반영됨 (더티 체킹)

        return userEntity; // 예시로 엔티티 반환. 실제로는 DTO 사용 권장
    }

}
