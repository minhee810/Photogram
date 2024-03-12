package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserUpdateDto {

    private String name;
    private String password;
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 위험 - 코드 수정 필요
    public User toEntity() {
        return User.updateBuilder()
                .name(name)
                .password(password)
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender).build();
    }




}
