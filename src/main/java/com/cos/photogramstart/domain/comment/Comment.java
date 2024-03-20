package com.cos.photogramstart.domain.comment;

import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Constraint;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100, nullable = false)
    private String content;

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER) // EAGER 즉시 로딩 전략
    private User user;


    @JoinColumn(name = "imageId")
    @ManyToOne(fetch = FetchType.EAGER)  // EAGER 즉시 로딩 전략
    private Image image;

    private LocalDateTime createDate;

    @PrePersist // db에 insert 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

}
