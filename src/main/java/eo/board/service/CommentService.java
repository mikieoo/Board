package eo.board.service;

import eo.board.dto.CommentRequest;
import eo.board.dto.CommentResponse;
import eo.board.entity.Board;
import eo.board.entity.Comment;
import eo.board.entity.User;
import eo.board.repository.BoardRepository;
import eo.board.repository.CommentRepository;
import eo.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long save(String nickname, Long boardId, CommentRequest request) {
        User user = userRepository.findByNickname(nickname);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        request.setUser(user);
        request.setBoard(board);

        Comment savedComment = commentRepository.save(request.toEntity());
        return savedComment.getId();
    }

    @Transactional
    public List<CommentResponse> findAll(Long boardId) {
        return commentRepository.findByBoardId(boardId)
                .stream()
                .map(CommentResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse findById(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->  new RuntimeException("Comment not found"));
        return new CommentResponse(comment);
    }

    @Transactional
    public CommentResponse update(Long boardId, Long id, CommentRequest request) {
        Comment comment = commentRepository.findByBoardIdAndId(boardId, id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.update(request.getComment());

        Comment updateComment = commentRepository.save(comment);
        return new CommentResponse(updateComment);
    }

    @Transactional
    public void delete(Long boardId, Long id) {
        Comment comment = commentRepository.findByBoardIdAndId(boardId, id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }

}