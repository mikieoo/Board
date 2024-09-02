package eo.board.repository;

import eo.board.entity.Board;
import eo.board.entity.Comment;
import eo.board.entity.Likes;
import eo.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUserAndBoard(User user, Board board);
    Optional<Likes> findByUserAndComment(User user, Comment comment);

    void deleteByUserAndBoard(User user, Board board);
    void deleteByUserAndComment(User user, Comment comment);
}
