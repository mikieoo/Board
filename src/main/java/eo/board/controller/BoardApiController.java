package eo.board.controller;

import eo.board.dto.BoardRequest;
import eo.board.dto.BoardResponse;
import eo.board.dto.SessionUser;
import eo.board.entity.Board;
import eo.board.entity.LoginUser;
import eo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {

    private final BoardService boardService;

    // 게시물 생성
    @PostMapping("/boards")
    public ResponseEntity<?> createBoard(@RequestBody BoardRequest request, @LoginUser SessionUser user) {
        return ResponseEntity.ok(boardService.save(user.getNickname(), request));
    }

    // 게시물 전체 조회
    @GetMapping("/boards/list")
    public ResponseEntity<List<BoardResponse>> findAllBoards() {
        List<BoardResponse> boards = boardService.findAll();
        return ResponseEntity.ok().body(boards);
    }

    // 게시물 조회
    @GetMapping("/boards/{id}")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable Long id){
        BoardResponse response = boardService.findById(id);
        return ResponseEntity.ok().body(response);
    }

    // 게시물 수정
    @PutMapping("/boards/{id}")
    public ResponseEntity<BoardResponse> updateBoard(@PathVariable Long id, @RequestBody BoardRequest request){
        BoardResponse updateBoard = boardService.update(id, request);
        return ResponseEntity.ok().body(updateBoard);
    }

    // 게시물 삭제
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Board> deleteBoard(@PathVariable Long id){
        boardService.delete(id);
        return ResponseEntity.ok().build();
    }

}