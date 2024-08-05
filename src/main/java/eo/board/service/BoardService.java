
package eo.board.service;

import eo.board.dto.BoardRequest;
import eo.board.dto.BoardResponse;
import eo.board.entity.Board;
import eo.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board save(BoardRequest request) {
        return boardRepository.save(request.toEntity());
    }

    public List<Board> findAll() {
        return boardRepository.findAllByOrderByIdDesc();
    }

    public Board findById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Board update(Long id, BoardRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        board.update(request.getTitle(), request.getContent(), request.getWriter());

        return boardRepository.save(board);
    }

    // 페이징 처리
    public Page<Board> paging(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

}
