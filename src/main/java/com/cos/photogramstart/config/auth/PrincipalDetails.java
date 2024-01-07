package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 사용자 세부 정보 클래스 UserDetails 구현
 */
@Data
public class PrincipalDetails implements UserDetails {

    private static final long serialVersionUID = 1L;

    // 추가
    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }


    // 권한 : 한 개가 아닐 수 있음 (3개 이상의 권한)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> {return user.getRole();});
        return collector;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }


    // 계정 만료 되었는지 확인하는 메서드
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // 개인정보 보호법
}
