package com.example.blog2.model.board;

import com.example.blog2.model.user.User;
import lombok.*;
import net.bytebuddy.asm.Advice;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board_tb")
@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 한면의 user가 여러개의 게시글 작성가능
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private String title;
    @Lob // 4GByte
    private String content;
    @Lob
    // content에 등록된 사진중 하나를 임의로 선정하여 보여준다.
    private String thumbnail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
