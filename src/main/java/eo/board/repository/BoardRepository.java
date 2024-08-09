package eo.board.repository;

import eo.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 조회
    List<Board> findAllByOrderByIdDesc();

    // 검색
    Page<Board> findByTitleContaining(String keyword, Pageable pageable);

    // 조회수
    @Modifying // 데이터의 변경이 있는 삽입, 수정, 삭제 쿼리 사용시 필요한 어노테이션
    @Query("update Board b set b.viewCount = b.viewCount + 1 where b.id = :id")
    void updateView(Long id);

}
