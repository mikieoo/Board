package eo.board.dto;

import eo.board.entity.Board;
import eo.board.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardResponse {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private LocalDateTime createdDate; // 생성일
    private LocalDateTime modifiedDate; // 수정일
    private int viewCount; // 조회수
    private Long userId;
    private List<CommentResponse> comments;

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
        this.viewCount = board.getViewCount();
        this.userId = board.getUser().getId();
        this.comments = board.getComments().stream().map(CommentResponse::new).collect(Collectors.toList());
    }

}
