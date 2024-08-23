package eo.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; // 아이디

    @Column(nullable = false)
    private String nickname; // 이름(닉네임)

    @Column(length = 100)
    private String password; // 패스워드

    @Column(nullable = false)
    private String email; // 이메일

    @Column(name = "profile_picture")
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // 권한 처리 (사용자 혹은 관리자)

    @OneToMany(mappedBy = "user")
    private List<Board> boards;

    public User update(String nickname, String picture, String password) {
        this.nickname = nickname;
        this.picture = picture;
        this.password = password;

        return this;
    }

    public String getRoleValue() {
        return this.role.getValue();
    }

}
