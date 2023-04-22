package com.example.blog2.core.auth;

import com.example.blog2.model.user.User;
import com.example.blog2.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User userPS = userRepository.findByUserName(username).orElseThrow(
            () -> new UsernameNotFoundException("Bad Credential") // 인증 안됨
        );
        return new MyUserDetails(userPS);
    }
}
