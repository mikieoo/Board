package eo.board.dto;

import eo.board.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class BoardResponse {

    private final Long id; // PK
    private final String title; // 제목
    private final String content; // 내용
    private final String writer; // 작성자
    private final LocalDateTime createdDate; // 생성일
    private final LocalDateTime modifiedDate; // 수정일

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.createdDate = board.getCreatedDate();
        this.modifiedDate = board.getModifiedDate();
    }


}
