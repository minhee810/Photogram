package com.cos.photogramstart.domain.user;

// JPA - Java Persistence Api (자바로 데이터를 영구적으로 저장(DB)할 수 있는 api를 제공)


import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Entity // db에 테이블을 생성
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호증가 전략이 데이터베이스를 따라간다.
    private int id;  // 큰 사용자를 둘 것이 아니기 때문에 long 이 아닌 int 로 설정.
    @Column(length = 100, unique = true) // oauth2 로그인을 위해 컬럼을 늘림
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;

    private String website;


    private String bio; // 자기소개
    @Column(nullable = false)
    private String email;


    private String phone;


    private String gender;

    private String profileImageUrl;

    private String role; // 권한

    // 연관관계의 주인이 아니므로 테이블에 컬럼 생성하지 않도록 설정
    // User 를 Select 할 때 해당 User id 로 등록된 image 모두 들고오도록
    // Lazy = User 를 Select 할 때 해당 User id 로 등록된 image 가져오지 않도록 - 대신 getImages()함수의 image 들이 호출될 때 가져옴,
    // Eager = User 를 Select 할 때 해당 User id로 등록된 image 들을 전부 Join 해서 가져오도록
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"user"}) // image 내부에 있는 user 를 무시하고 파싱하도록 설정
    private List<Image> images;  // 양방향 매핑 컬럼


    private LocalDateTime createDate;

    @Builder(builderMethodName = "signupBuilder")
    public User(String username, String password, String name, String email, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.email = email;
    }

    @Builder(builderMethodName = "updateBuilder", toBuilder = true)
    public User(String name, String password, String website, String bio, String phone, String gender) {
        this.name = name;
        this.password = password;
        this.website = website;
        this.bio = bio;
        this.phone = phone;
        this.gender = gender;
    }

    @PrePersist // db에 insert 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + ", name='" + name + '\'' + ", website='" + website + '\'' + ", bio='" + bio + '\'' + ", email='" + email + '\'' + ", phone='" + phone + '\'' + ", gender='" + gender + '\'' + ", profileImageUrl='" + profileImageUrl + '\'' + ", role='" + role + '\'' + ", createDate=" + createDate + '}';
    }
}
