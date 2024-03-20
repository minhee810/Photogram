package com.cos.photogramstart.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

//    @Modifying
//    @Query(value = "INSERT INTO comment(content, imageId, userId, createDate) VALUES (:content, :imageId, :userId, now())", nativeQuery = true)
//    Comment mSave(String content, int imageId, int userId);


}
