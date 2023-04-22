package com.example.blog2.service;

import com.example.blog2.dto.board.BoardRequest;
import com.example.blog2.model.board.BoardRepository;
import com.example.blog2.model.user.User;
import com.example.blog2.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public void 글쓰기(BoardRequest.SaveInDTO saveInDTO, Long userId) {

        try {
            // 유저 존재 확인
            User userPS = userRepository.findById(userId).orElseThrow(
                    () -> new RuntimeException("유저를 찾을 수 없습니다")
            );

            // 게시글 쓰기
            boardRepository.save(saveInDTO.toEntity(userPS));

        } catch (Exception e) {
            throw new RuntimeException("글쓰기 실패 : " + e.getMessage());
        }
    }
}
