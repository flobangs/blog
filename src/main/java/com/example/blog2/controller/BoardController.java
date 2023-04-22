package com.example.blog2.controller;

import com.example.blog2.core.auth.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    // REST API 주소 설계 규칙에서 자원에는 복수를 붙인다. (정석)
    // 하지만 여기서는 복수를 붙이지 않는다.
    @GetMapping({"/", "/board"})
    public String main() {
        return "board/main";
    }

    @GetMapping("/s/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }

}
