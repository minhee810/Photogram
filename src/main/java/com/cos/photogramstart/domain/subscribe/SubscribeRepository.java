package com.cos.photogramstart.domain.subscribe;

import com.cos.photogramstart.web.dto.subscribe.SubscribeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {


    @Modifying
    @Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
    void mSubscribe(int fromUserId, int toUserId);


    @Query(value = "DELETE FROM subscribe WHERE fromUserId=:fromUserId AND toUserId=:toUserId", nativeQuery = true)
    void mUnSubscribe(int fromUserId, int toUserId);


    @Query(value = "SELECT count(*) FROM Subscribe WHERE fromUserId =:principalId AND toUserId =:pageUserId", nativeQuery = true)
    int mSubscribeState(int principalId, int pageUserId);

    @Query(value = "SELECT count(*) FROM Subscribe WHERE fromUserId =:pageUserId", nativeQuery = true)
    int mSubscribeCount(int pageUserId);

}
