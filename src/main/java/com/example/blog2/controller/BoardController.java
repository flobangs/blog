package com.example.blog2.controller;

import com.example.blog2.core.auth.MyUserDetails;
import com.example.blog2.dto.board.BoardRequest;
import com.example.blog2.model.board.BoardRepository;
import com.example.blog2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

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

    @PostMapping("/s/board/save")
    public String save(
            BoardRequest.SaveInDTO saveInDTO,
            // view에서만 세션에서 들고오고 여기서는 @Auth... 를 사용한다.
            @AuthenticationPrincipal MyUserDetails myUserDetails) {

        boardService.글쓰기(saveInDTO, myUserDetails.getUser().getId());
        return "redirect:/";
    }

}
