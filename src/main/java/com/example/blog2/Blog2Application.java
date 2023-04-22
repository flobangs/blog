package com.example.blog2;

import com.example.blog2.model.user.User;
import com.example.blog2.model.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Blog2Application {


    @Bean
    CommandLineRunner init(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        return agrs -> {
            User ssar = User.builder()
                    .username("ssar")
                    .password(passwordEncoder.encode("1234"))
                    .email("ssar@nate.com")
                    .role("USER")
                    .status(true)
                    .profile("psrson.png")
                    .build();

            userRepository.save(ssar);
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(Blog2Application.class, args);
    }

}
