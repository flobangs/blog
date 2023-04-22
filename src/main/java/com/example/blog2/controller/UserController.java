package com.example.blog2.controller;

import com.example.blog2.dto.user.UserRequest;
import com.example.blog2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 인증이 되지 않은 상태에서 인증과 관련된 주소는 앞에 엔티티 적지 않기
    // write (POST 사용) : save, delete, update
    // read (GET 사용) select

    @PostMapping("/join")
    public String join(UserRequest.JoinInDto joinInDto) {

        userService.회원가입(joinInDto);
        return "redirect:/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginFrom() {
        return "user/loginForm";
    }
    
}
