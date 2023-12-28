package com.cos.photogramstart.domain.user;

// JPA - Java Persistence Api (자바로 데이터를 영구적으로 저장(DB)할 수 있는 api를 제공)


import com.nimbusds.oauth2.sdk.TokenIntrospectionSuccessResponse;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@AllArgsConstructor
//@Builder
@NoArgsConstructor
@Data
@Entity // db에 테이블을 생성
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호증가 전략이 데이터베이스를 따라간다.
    private int id;
    @Column(length = 20, unique = true)
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
    private LocalDateTime createDate;
    @PrePersist // db에 insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }

    @Builder
    public User(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
