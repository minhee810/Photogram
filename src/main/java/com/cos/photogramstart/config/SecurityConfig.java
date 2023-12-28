package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity // 해당 파일로 security 활성화
@Configuration // IoC -> 빈으로 등록 메모리에 뜨게함 -> 기존 시큐리티가 가로채고 있음
public class SecurityConfig extends WebSecurityConfigurerAdapter {  // 상속


    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); // -> 지우면 기존 시큐리티가 갖고 있는 기능이 다 비활성화 됨.
        http.csrf().disable(); // 토큰을 갖고 있는지 아닌지 구분하지 않겠다.
        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin") // GET
                .loginProcessingUrl("/auth/signin")// post -> 스프링 시큐리티가 로그인 프로세스 진행
                .defaultSuccessUrl("/");
    }
}
