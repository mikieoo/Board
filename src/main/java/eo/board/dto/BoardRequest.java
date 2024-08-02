package eo.board.dto;

import eo.board.entity.Board;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequest {

    private String title; // 제목
    private String content; // 내용

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .build();
    }

}
