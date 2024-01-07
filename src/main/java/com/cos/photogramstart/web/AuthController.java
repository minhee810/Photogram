package com.cos.photogramstart.web;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

// 인증을 위한 컨트롤러
@RequiredArgsConstructor  // final 이 붙은 생성자들을 자동으로 생성해주는 롬복 어노테이션
@Controller // IoC 등록, 파일을 리턴하는 컨트롤러
public class AuthController {

    private final AuthService authService;

//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/auth/signin")
    public String signinForm(){
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm(){
        return "auth/signup";
    }

    // 회원가입 버튼 -> auth/signup => /auth/signin
    // 회원가입 버튼 x
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult){ // key=value(x-www-form-urlencoded)

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error:bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
//            throw new RuntimeException("유효성 검사 실패");
            throw new CustomValidationException("유효성 검사 실패함", errorMap);
        }else {
            log.info(signupDto.toString());

            User user = signupDto.toEntity();
            log.info(user.toString());

            User userEntity = authService.join(user);
            log.info(userEntity.toString());
            return "auth/signin"; // 회원가입 성공 시 로그인 페이지로 이동
        }


    }
}
