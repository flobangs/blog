package com.example.blog2;

import com.example.blog2.dto.board.BoardRequest;
import com.example.blog2.model.board.Board;
import com.example.blog2.model.board.BoardRepository;
import com.example.blog2.model.user.User;
import com.example.blog2.model.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Blog2Application extends DummyEntity {


    @Profile("dev")
    @Bean
    CommandLineRunner init(
            UserRepository userRepository,
            BoardRepository boardRepository,
            BCryptPasswordEncoder passwordEncoder) {
        return agrs -> {
            User ssar = newUser("ssar", passwordEncoder);
            User cos = newUser("cos", passwordEncoder);
            userRepository.saveAll(Arrays.asList(ssar, cos));

            List<Board> boardList = new ArrayList<>();

            for(int i = 1;i < 11;i++)
                boardList.add(newBoard("제목" + i, ssar));

            for(int i = 11;i < 21;i++)
                boardList.add(newBoard("제목" + i, cos));

            boardRepository.saveAll(boardList);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Blog2Application.class, args);
    }
}



