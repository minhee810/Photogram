package com.cos.photogramstart.handler.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAdvice {

    @Around("execution(* com.cos.photogramstart.web.api.*Controller.*(..))")
    public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("web api 컨트롤러 =======================================");


        return proceedingJoinPoint.proceed(); // profile 함수가 실행됨.
    }

    @Around("execution(* com.cos.photogramstart.web.*Controller.*(..))")
    public Object advice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("web 컨트롤러 ===========================================");

        return proceedingJoinPoint.proceed();
    }

}
