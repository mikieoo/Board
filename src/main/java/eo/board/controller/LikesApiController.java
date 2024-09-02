package eo.board.controller;

import eo.board.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
public class LikesApiController {

    private final LikesService likesService;

    @PostMapping("/board/{boardId}")
    public ResponseEntity<Boolean> likeBoard(@RequestParam Long userId, @PathVariable Long boardId) {
        boolean liked = likesService.toggleLikeBoard(userId, boardId);
        return ResponseEntity.ok(liked);
    }

    @PostMapping("/comment/{commentId}")
    public ResponseEntity<Boolean> toggleLikeComment(@RequestParam Long userId, @PathVariable Long commentId) {
        boolean isLiked = likesService.toggleLikeComment(userId, commentId);
        return ResponseEntity.ok(isLiked);
    }

}
