package eo.board.controller;

import eo.board.dto.SessionUser;
import eo.board.dto.UserRequest;
import eo.board.entity.User;
import eo.board.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserViewController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users/{username}")
    public String myInfo(@PathVariable String username, Model model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", new SessionUser(user));

        return "myInfo";
    }

    @PostMapping("/users/{username}/update")
    public String updateUser(@PathVariable String username, Model model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", new SessionUser(user));

        return "newInfo";
    }


}
