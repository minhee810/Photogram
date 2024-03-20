package com.cos.photogramstart.web.dto.auth;
import com.cos.photogramstart.domain.user.User;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupDto {

//    @NotBlank // 무조건 받아야 하는 값
    @Size(min = 2, max = 20)
    private String username;
    private String password;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    public User toEntity() {
        return User.signupBuilder()
                .username(username)
                .password(password)
                .email(email)
                .name(name)
                .build();
    }


}
