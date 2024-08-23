package eo.board.dto;

import eo.board.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class CommentResponse {
    private final Long id;
    private final String comment;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;
    private final String nickname;
    private final Long userId;
    private final Long boardId;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.userId = comment.getUser().getId();
        this.nickname = comment.getUser().getNickname();
        this.boardId = comment.getBoard().getId();
    }

}
