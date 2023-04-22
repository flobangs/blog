package com.example.blog2.service;

import com.example.blog2.dto.user.UserRequest;
import com.example.blog2.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    // SecurityConfig에 Bean으로 등록함
    private final BCryptPasswordEncoder passwordEncoder;

    // 트렌젝션 종료시에 더티체킹, DB 세션 종료(OSIV=false로 설정했음)
    // application-dev.yml
    // OSIV를 false로 하면 트렌젝션이 종료시점에 세션이 종료되어
    // controller에서 더이상 lazy 로드 할 수 없다. select가 안됨
    // insert, uipdate, delete는 -> save()오류을 잡아 낼 수 었으므로
    // try .. catch()로 잡아야 한다.
    @Transactional
    public void 회원가입(UserRequest.JoinInDto joinInDto) {
        try {
            // 1. password 암호화
            joinInDto.setPassword(passwordEncoder.encode(joinInDto.getPassword()));

            // 2. DB 저장
            // 응답의 DTO가 필요없다. 바로 뿌려줄 예정임.
        } catch(Exception e) {
            throw new RuntimeException("회원가입 오류 : " + e.getMessage());
        }
        userRepository.save(joinInDto.toEntity());
    }
}
