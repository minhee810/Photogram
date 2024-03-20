package com.cos.photogramstart.domain.likes;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "likes_uk",
                        columnNames = {"imageId", "userId"}  // 실제 db 컬럼명
                )
        }
)
public class Likes {  // N , N

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    // 무한 참조
    @JoinColumn(name = "imageId")
    @ManyToOne
    private Image image; // 1


    // 오류 발생 후 수정하기
    @JsonIgnoreProperties({"images"}) // 추가
    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;  // 1

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
