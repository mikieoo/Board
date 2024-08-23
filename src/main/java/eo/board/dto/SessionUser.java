package eo.board.dto;

import eo.board.entity.Role;
import eo.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class SessionUser implements Serializable {

    private final Long id;
    private final String username;
    private final String nickname;
    private final String email;
    private final String picture;
    private final String password;
    private final Role role;

    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

}
