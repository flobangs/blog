package com.example.blog2.model.user;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 개발자가 new 하지 못하게 막는다.
@Getter
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 20)
    private String username;
    @Column(length = 60) // 실제로는 20글자만 받는다.
    private String password;
    @Column(length = 50)
    private String email;
    private String role; // USER(고객)
    private String profile; // 유저 프로필 사진 경로
    private Boolean status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 프로필 사진 변경
    public void changeProfile(String profile){
        this.profile = profile;
    }

    // 회원 정보 수정
    public void update(String password, String emㅇail) {
        this.password = password;
        this.email = email;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

