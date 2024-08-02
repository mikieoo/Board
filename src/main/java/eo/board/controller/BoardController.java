package eo.board.controller;

import eo.board.dto.BoardRequest;
import eo.board.dto.BoardResponse;
import eo.board.entity.Board;
import eo.board.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BlogService blogService;

    // 게시물 생성
    @PostMapping("/boards")
    public ResponseEntity<Board> createBoard(@RequestBody BoardRequest request) {
        Board savedBoard = blogService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBoard);
    }

    // 게시물 전체 조회
    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponse>> findAllBoards() {
        List<BoardResponse> boards = blogService.findAll()
                .stream()
                .map(BoardResponse::new)
                .toList();

        return ResponseEntity.ok().body(boards);
    }

    // 게시물 조회
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable Long id){
        Board board = blogService.findById(id);

        return ResponseEntity.ok().body(new BoardResponse(board));
    }

    // 게시물 수정
    @PutMapping("/boards/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody BoardRequest request){
        Board updateBoard = blogService.update(id, request);

        return ResponseEntity.ok().body(updateBoard);
    }

    // 게시물 삭제
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Board> deleteBoard(@PathVariable Long id, @RequestBody BoardRequest request){
        blogService.delete(id);

        return ResponseEntity.ok().build();
    }

}
