package eo.board.controller;

import eo.board.dto.UserRequest;
import eo.board.entity.User;
import eo.board.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user")
    public String saveUser(@RequestBody UserRequest request) {
        request.setPicture("/images/default-profile.png");
        userService.save(request);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response,
                    authentication);
        }

        return "redirect:/login";
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @ModelAttribute UserRequest request,
                                           @RequestPart(value = "profilePicture", required = false) MultipartFile profilePicture) {
        User updateUser = userService.update(id, request, profilePicture);
        return ResponseEntity.ok().body(updateUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/check-username")
    public ResponseEntity<Boolean> checkUsername(@RequestParam String username) {
        boolean available = userService.existsByUsername(username);
        return ResponseEntity.ok(available);
    }

    @GetMapping("/user/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        boolean available = userService.existsByEmail(email);
        return ResponseEntity.ok(available);
    }

}
