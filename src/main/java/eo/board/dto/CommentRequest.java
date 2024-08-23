package eo.board.dto;

import eo.board.entity.Board;
import eo.board.entity.Comment;
import eo.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {
    private Long id;
    private String comment;
    private User user;
    private Board board;

    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .comment(comment)
                .user(user)
                .board(board)
                .build();
    }
}
