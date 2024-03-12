package com.cos.photogramstart.domain.user;

// JPA - Java Persistence Api (자바로 데이터를 영구적으로 저장(DB)할 수 있는 api를 제공)


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(length = 20, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;

    @Column
    private String website;

    @Column
    private String bio; // 자기소개
    @Column(nullable = false)
    private String email;

    @Column
    private String phone;

    @Column
    private String gender;

    @Column
    private String profileImageUrl;

    @Column
    private String role; // 권한
    @Column
    private LocalDateTime createDate;

    @PrePersist // db에 insert 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    @Builder(builderMethodName = "signupBuilder")
    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
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

}
