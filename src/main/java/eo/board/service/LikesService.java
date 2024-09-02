package eo.board.service;

import eo.board.entity.Board;
import eo.board.entity.Comment;
import eo.board.entity.Likes;
import eo.board.entity.User;
import eo.board.repository.BoardRepository;
import eo.board.repository.CommentRepository;
import eo.board.repository.LikesRepository;
import eo.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public boolean toggleLikeBoard(Long userId, Long boardId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid board ID"));

        Optional<Likes> existingLike = likesRepository.findByUserAndBoard(user, board);

        if (existingLike.isPresent()) {
            // 좋아요가 이미 존재할 경우, 삭제
            likesRepository.delete(existingLike.get());
            board.setLikeCount(board.getLikeCount() - 1);
            boardRepository.save(board);
            return false; // 좋아요가 취소됨
        } else {
            // 좋아요가 존재하지 않을 경우, 추가
            likesRepository.save(new Likes(user, board));
            board.setLikeCount(board.getLikeCount() + 1);
            boardRepository.save(board);
            return true; // 좋아요가 추가됨
        }
    }

    @Transactional
    public boolean toggleLikeComment(Long userId, Long commentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID"));

        Optional<Likes> existingLike = likesRepository.findByUserAndComment(user, comment);

        if (existingLike.isPresent()) {
            // 좋아요가 이미 존재할 경우, 삭제
            likesRepository.delete(existingLike.get());
            comment.setLikeCount(comment.getLikeCount() - 1);
            commentRepository.save(comment);
            return false; // 좋아요가 취소됨
        } else {
            // 좋아요가 존재하지 않을 경우, 추가
            likesRepository.save(new Likes(user, comment));
            comment.setLikeCount(comment.getLikeCount() + 1);
            commentRepository.save(comment);
            return true; // 좋아요가 추가됨
        }
    }
}
