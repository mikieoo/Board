package eo.board.dto;

import eo.board.entity.Role;
import eo.board.entity.User;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    private String username;
    private String nickname;
    private String password;
    private String email;
    private Role role;

    public User toEntity() {
        return User.builder()
                .username(username)
                .nickname(nickname)
                .password(password)
                .email(email)
                .role(role)
                .build();
    }

}
