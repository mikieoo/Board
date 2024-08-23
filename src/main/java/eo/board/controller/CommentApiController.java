package eo.board.controller;

import eo.board.dto.CommentRequest;
import eo.board.dto.CommentResponse;
import eo.board.dto.SessionUser;
import eo.board.entity.LoginUser;
import eo.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<?> createComment(@LoginUser SessionUser user, @PathVariable Long boardId, @RequestBody CommentRequest request) {
        return ResponseEntity.ok(commentService.save(user.getNickname(), boardId, request));
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> getCommentById(@PathVariable Long id) {
        CommentResponse response = commentService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long boardId, @PathVariable Long commentId, @RequestBody CommentRequest request) {
        CommentResponse updateComment = commentService.update(boardId, commentId, request);
        return ResponseEntity.ok(updateComment);
    }

    @DeleteMapping("/boards/{boardId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
         commentService.delete(boardId, commentId);
         return ResponseEntity.ok().build();
    }

}