package com.cos.photogramstart.config.auth;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service // IoC등록 되면서 UserDetailsService를 덮음.
public class PrincipalDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(PrincipalDetailsService.class);
    private final UserRepository userRepository;

    // 1. password 는 알아서 체킹하니까 신경 쓸 필요 없다.
    // 2. return 이 잘 되면 자동으로 UserDetails 타입을 세션으로 만든다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("[loadUserByUsername] loadUserByUsername 수행. username : {}", username);

        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            return null;
        } else {
            return new PrincipalDetails(userEntity);
        }
    }
}
