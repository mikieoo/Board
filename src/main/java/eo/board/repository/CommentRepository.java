package eo.board.repository;

import eo.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoardId(Long boardId);
    Optional<Comment> findByBoardIdAndId(Long boardId, Long id);
}
