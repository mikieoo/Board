package eo.board.dto;

import eo.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponse {

    private Long id; // PK
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private LocalDateTime createdDate; // 생성일
    private LocalDateTime modifiedDate; // 수정일
    private int viewCount;

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
        this.viewCount = board.getViewCount();
    }


}
