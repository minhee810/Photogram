package com.cos.photogramstart.domain.image;

import com.cos.photogramstart.domain.comment.Comment;
import com.cos.photogramstart.domain.likes.Likes;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @JsonIgnoreProperties({"images"}) // user 정보가 갖고 있는 images 는 가져올 필요가 없음을 의미
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER)  // image 를 select 하면 user 정보를 같이 들고옴.
    private User user; // 연관관계의 경우 FK가 데이터 베이스에 저장됨.

    // 이미지 좋아요
    @JsonIgnoreProperties({"image"}) // image 를 리턴할 때 likes 를 리턴하게 되는데 그 때 likes 에 있는 image 리턴 막기
    @OneToMany(mappedBy = "image")
    private List<Likes> likes;


    // 이미지 댓글
    @OrderBy("id DESC")
    @JsonIgnoreProperties({"image"})
    @OneToMany(mappedBy = "image")
    private List<Comment> comments;

    private LocalDateTime createDate;

    @Transient // db 에 컬럼이 생기지 않음
    private boolean likeState;

    @Transient
    private int likeCount;

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

    // 오브젝트를 콘솔에 출력할 때 문제가 될 수 있어서 User 부분을 삭제한 toString() 재정의함.
//    @Override
//    public String toString() {
//        return "Image{" +
//                "id=" + id +
//                ", caption='" + caption + '\'' +
//                ", postImageUrl='" + postImageUrl + '\'' +
//                ", createDate=" + createDate +
//                '}';
//    }
}
