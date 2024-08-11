package eo.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username; // 아이디

    @Column(nullable = false, unique = true)
    private String nickname; // 이름(닉네임)

    @Column(nullable = false)
    private String password; // 패스워드

    @Column(nullable = false)
    private String email; // 이메일

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 권한 처리 (사용자 혹은 관리자)

}
