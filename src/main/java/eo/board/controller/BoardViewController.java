package eo.board.controller;

import eo.board.dto.BoardResponse;
import eo.board.entity.Board;
import eo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardViewController {

    private final BoardService boardService;

    // 홈 화면 (게시물 전체 조회)
    @GetMapping("/boards/list")
    public String getBoards(Model model, @PageableDefault Pageable pageable) {
        Page<Board> boardPage = boardService.paging(pageable);
        Page<BoardResponse> boardResponses = boardPage.map(BoardResponse::new);

        int totalPages = boardPage.getTotalPages();
        int currentPage = boardPage.getNumber() + 1; // 현재 페이지는 0부터 시작하므로 +1
        int pageGroupSize = 10;

        int startPage = (currentPage - 1) / pageGroupSize * pageGroupSize + 1;
        int endPage = Math.min(startPage + pageGroupSize - 1, totalPages);

        model.addAttribute("boards", boardResponses);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);

        return "home";
    }

    // 게시물 상세 조회
    @GetMapping("/boards/{id}")
    public String readBoard(@PathVariable Long id, Model model){
        Board board = boardService.findById(id);
        boardService.viewCount(id);
        model.addAttribute("board", new BoardResponse(board));

        return "read";
    }

    // 게시물 수정
    @GetMapping("/new-board")
    public String updateBoard(@RequestParam(required = false) Long id, Model model) {
        if(id == null) {
            model.addAttribute("board", new BoardResponse());
        } else {
            Board board = boardService.findById(id);
            model.addAttribute("board", new BoardResponse(board));
        }

        return "update";
    }



}
