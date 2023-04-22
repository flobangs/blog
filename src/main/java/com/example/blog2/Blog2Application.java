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
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class Blog2Application {


    @Bean
    CommandLineRunner init(
            UserRepository userRepository,
            BoardRepository boardRepository,
            BCryptPasswordEncoder passwordEncoder) {
        return agrs -> {
            User ssar = User.builder()
                    .username("ssar")
                    .password(passwordEncoder.encode("1234"))
                    .email("ssar@nate.com")
                    .role("USER")
                    .status(true)
                    .profile("psrson.png")
                    .build();
            User cos = User.builder()
                    .username("cos")
                    .password(passwordEncoder.encode("1234"))
                    .email("cos@nate.com")
                    .role("USER")
                    .status(true)
                    .profile("psrson.png")
                    .build();

            userRepository.saveAll(Arrays.asList(ssar, cos));

            Board b1 = Board.builder()
                    .title("제목1")
                    .content("내용1")
                    .user(ssar)
                    .thumbnail("/upload/person.png")
                    .build();
            Board b2 = Board.builder()
                    .title("제목2")
                    .content("내용2")
                    .user(cos)
                    .thumbnail("/upload/person.png")
                    .build();
            boardRepository.saveAll(Arrays.asList(b1, b2));
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Blog2Application.class, args);
    }

}
