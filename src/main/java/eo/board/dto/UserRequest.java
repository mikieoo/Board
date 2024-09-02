package eo.board.dto;

import eo.board.entity.Role;
import eo.board.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private Long id;
    private String username;
    private String nickname;
    private String password;
    private String picture;
    private String email;
    private Role role;

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .nickname(nickname)
                .password(password)
                .picture(picture)
                .email(email)
                .role(Role.USER)
                .build();
    }

}
