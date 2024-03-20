package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

// 인증을 위한 컨트롤러
@RequiredArgsConstructor  // final 이 붙은 생성자들을 자동으로 생성해주는 롬복 어노테이션
@Controller // IoC 등록, 파일을 리턴하는 컨트롤러
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    // 회원가입 버튼 -> auth/signup => /auth/signin
    // 회원가입 버튼 x
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // key=value(x-www-form-urlencoded)

        log.info(signupDto.toString());

        User user = signupDto.toEntity();
        log.info(user.toString());

        User userEntity = authService.join(user);
        log.info(userEntity.toString());

        // 로그를 남기는 후처리할 수 있음.
        return "auth/signin"; // 회원가입 성공 시 로그인 페이지로 이동

    }
}
