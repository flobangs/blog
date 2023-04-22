package com.example.blog2.dto.board;

import com.example.blog2.model.board.Board;
import com.example.blog2.model.user.User;
import lombok.Getter;
import lombok.Setter;

public class BoardRequest {

    @Getter
    @Setter
    public static class SaveInDTO {
        private String title;
        private String content;

        public Board toEntity(User user) {
            return Board.builder()
                    .user(user)
                    .title(title)
                    .content(content)
                    .thumbnail(null)
                    .build();
        }
    }
}
