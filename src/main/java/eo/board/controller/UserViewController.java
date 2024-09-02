package eo.board.controller;

import eo.board.dto.SessionUser;
import eo.board.entity.User;
import eo.board.service.UserService;
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

    @GetMapping("/users/{id}")
    public String myInfo(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", new SessionUser(user));

        return "myInfo";
    }

    @PostMapping("/users/{id}/update")
    public String updateUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", new SessionUser(user));

        return "newInfo";
    }

}