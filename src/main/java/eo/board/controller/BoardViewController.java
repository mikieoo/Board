package eo.board.controller;

import eo.board.dto.BoardResponse;
import eo.board.dto.CommentResponse;
import eo.board.dto.SessionUser;
import eo.board.service.BoardService;
import eo.board.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardViewController {

    private final BoardService boardService;
    private final HttpSession session;

    // 홈 화면 (게시물 전체 조회) & 페이징 처리 & 검색 기능 (ㅠㅠ)
    @GetMapping("/boards/list")
    public String getBoards(Model model,
                            @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                            @RequestParam(required = false) String keyword) {

        // {닉네임} 환영인사 문구
        SessionUser user = (SessionUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("nickname", user.getNickname());
            model.addAttribute("id", user.getId());
        }

        // 검색 및 페이징 처리
        Page<BoardResponse> boardPage;
        if (keyword != null && !keyword.isEmpty()) {
            boardPage = boardService.search(keyword, pageable);
        } else {
            boardPage = boardService.paging(pageable);
        }

        int totalPages = boardPage.getTotalPages();
        int currentPage = boardPage.getNumber() + 1; // 현재 페이지는 0부터 시작하므로 +1
        int pageGroupSize = 10;

        int startPage = 1;
        int endPage = 1;

        if (totalPages > 0) {
            startPage = (currentPage - 1) / pageGroupSize * pageGroupSize + 1;
            endPage = Math.min(startPage + pageGroupSize - 1, totalPages);
        }

        model.addAttribute("keyword", keyword);

        if(keyword != null && boardPage.isEmpty()) {
            model.addAttribute("message", "검색 결과가 없습니다.");
        }

        model.addAttribute("boards", boardPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);

        return "home";
    }


    // 게시물 상세 조회
    @GetMapping("/boards/{id}")
    public String readBoard(@PathVariable Long id, Model model){
        BoardResponse response = boardService.findById(id);
        List<CommentResponse> comments = response.getComments();

        SessionUser user = (SessionUser) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("nickname", user.getNickname());
            model.addAttribute("userId", user.getId());
            if(response.getUserId().equals(user.getId())){
                model.addAttribute("writer", true);
            }
        }

        boardService.viewCount(id);
        model.addAttribute("comments", comments);
        model.addAttribute("board", response);

        return "read";
    }

    // 게시물 수정
    @GetMapping("/new-board")
    public String updateBoard(@RequestParam(required = false) Long id, Model model) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        if(user != null) {
            model.addAttribute("nickname", user.getNickname());
        }

        if(id == null) {
            model.addAttribute("board", new BoardResponse());
        } else {
            BoardResponse response = boardService.findById(id);
            model.addAttribute("board", response);
        }

        return "update";
    }


}
