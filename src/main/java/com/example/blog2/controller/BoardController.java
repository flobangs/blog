package com.example.blog2.controller;

import com.example.blog2.core.auth.MyUserDetails;
import com.example.blog2.dto.board.BoardRequest;
import com.example.blog2.model.board.Board;
import com.example.blog2.model.board.BoardRepository;
import com.example.blog2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;

    // REST API 주소 설계 규칙에서 자원에는 복수를 붙인다. (정석)
    // 하지만 여기서는 복수를 붙이지 않는다.
    @GetMapping({"/", "/board"})
    public String main(@RequestParam(defaultValue = "0")int page, Model model) {
        PageRequest pageRequest =
                PageRequest.of(page, 8, Sort.by("id").descending());
        // 현재는 영속화 되어 있지 않다.
        // boardService()에서 findAll()을 했지만 .yml의 open-in-view:false로 되어있기 때문에
        // controller로 돌아오는 순간 세션과 상관없게 되어 비영속화 된다.
        // open-in-view:true로 하면 여기서 boardPG는 영속화 된다.
        Page<Board> boardPG = boardService.글목록보기(pageRequest);
        model.addAttribute("boardPG", boardPG);
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
