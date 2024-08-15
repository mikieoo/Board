package eo.board.dto;

import eo.board.entity.Board;
import eo.board.entity.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequest {

    private Long id;
    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private User user;


    public Board toEntity(User user) {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .user(user)
                .build();
    }

}
