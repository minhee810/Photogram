package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption;

    private String postImageUrl; // 사진을 전송 받아서 서버의 특정 폴더에 저장을 할 것이고, db 에는 저장된 경로를 저장할 것임.

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user; // 연관관계의 경우 FK가 데이터 베이스에 저장됨.

    // 이미지 좋아요

    // 이미지 댓글

    private LocalDateTime createDate;

    @PrePersist // db에 insert 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    @Builder(builderMethodName = "imageBuilder")
    public Image(String caption, String postImageUrl, User user) {
        this.caption = caption;
        this.postImageUrl = postImageUrl;
        this.user = user;
    }
}
