package com.cos.photogramstart.handler;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@RestController // 데이터를 리턴 -> 응답
@ControllerAdvice // 모든 예외를 낚아 챔.
public class ControllerExceptionHandler {
    @ExceptionHandler(CustomValidationException.class)    // RuntimeException 이 발생하는 모든 예외를 이 메서드가 가로챔.
    public String validationException(CustomValidationException e) {
        // CMRespDto, Script 비교
        // 1. 클라이언트(브라우저)에게 응답할 때는 Script 가 좋음
        // 2. AJax 통신 - CMRespDto
        // 4. Android 통신 - CMRespDto
        return Script.back(e.getErrorMap().toString());
    }
}
