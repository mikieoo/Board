package eo.board.service;

import eo.board.dto.BoardRequest;
import eo.board.dto.BoardResponse;
import eo.board.entity.Board;
import eo.board.entity.User;
import eo.board.repository.BoardRepository;
import eo.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public Long save(String nickname, BoardRequest request) {
        User user = userRepository.findByNickname(nickname);
        request.setUser(user);

        Board board = request.toEntity(user);
        boardRepository.save(board);

        return board.getId();
    }

    public List<BoardResponse> findAll() {
        return boardRepository.findAllByOrderByIdDesc()
                .stream()
                .map(BoardResponse::new)
                .collect(Collectors.toList());
    }

    public BoardResponse findById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));
        return new BoardResponse(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public BoardResponse update(Long id, BoardRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found"));

        board.update(request.getTitle(), request.getContent());

        Board updatedBoard = boardRepository.save(board);
        return new BoardResponse(updatedBoard);
    }

    // 페이징 처리
    public Page<BoardResponse> paging(Pageable pageable) {
        return boardRepository.findAll(pageable)
                .map(BoardResponse::new);
    }

    // 조회수
    @Transactional
    public void viewCount(Long id){
        boardRepository.updateView(id);
    }

    // 검색 및 페이징 처리
    public Page<BoardResponse> search(String keyword, Pageable pageable) {
        return boardRepository.findByTitleContaining(keyword, pageable)
                .map(BoardResponse::new);
    }


}
