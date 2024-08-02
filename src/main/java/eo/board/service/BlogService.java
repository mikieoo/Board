package eo.board.service;

import eo.board.dto.BoardRequest;
import eo.board.entity.Board;
import eo.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

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

        board.update(request.getTitle(), request.getContent());

        return boardRepository.save(board);
    }

}
