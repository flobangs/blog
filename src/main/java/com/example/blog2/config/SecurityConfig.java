package com.example.blog2.config;

import com.example.blog2.core.auth.MyUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpSession;

@Slf4j
@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1. CSRF 해제 (개발할 때는 |해제 - POST 맨을 사용할 수 없다)
        // 필요할 때 지우면 된다.
        http.csrf().disable();

        // >> frame option 해제 (시큐리티 h2-console) 접속 허용을 위해 사용)
        http:
        http.headers().frameOptions().disable();

        // 2. Form 로그인 설정
        http.formLogin()
                .loginPage("/loginForm")
                // localhost:8080/login 호출 시
                // MyUserDetailsService가 호출 된다.
                // username으로 user 영속화한다.
                // post / x-www 호출
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    log.debug("디버그 : 로그인 성공");
                    // 로그인이 성공하면 UserService가 실행됨

                    // view에서 아용하려고 사용한다.
                    MyUserDetails myUserDetails = (MyUserDetails)authentication.getPrincipal();
                    HttpSession session = request.getSession();
                    session.setAttribute("sessionUser", myUserDetails.getUser());

                    response.sendRedirect("/");
                })
                .failureHandler((request, response, exception) -> {
                    log.debug("디버그 : 로그인 실패 - " + exception.getMessage());
                    response.sendRedirect("/loginForm");
                });

        // 3. 인증, 권한 필터 설정
        // 주소가 s로 시작하지 않으면 들어갈 수 없다.
        http.authorizeRequests(
                authorize -> authorize.antMatchers("/s/**")
                        .authenticated()
        );

        return http.build();
    }

}
