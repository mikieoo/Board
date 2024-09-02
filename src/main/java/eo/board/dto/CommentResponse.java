package eo.board.dto;

import eo.board.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String comment;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String nickname;
    private Long userId;
    private Long boardId;
    private String profileImage;
    private int likeCount;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createdDate = comment.getCreatedDate();
        this.modifiedDate = comment.getModifiedDate();
        this.userId = comment.getUser().getId();
        this.nickname = comment.getUser().getNickname();
        this.boardId = comment.getBoard().getId();
        this.profileImage = comment.getUser().getPicture();
        this.likeCount = comment.getLikeCount();
    }

}
