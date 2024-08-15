package eo.board;

import eo.board.entity.Board;
import eo.board.entity.User;
import eo.board.repository.BoardRepository;
import eo.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
public class BoardTest {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Test
    public void test() {
        Board board = boardRepository.findById(113L).get();
        log.info("Title : " +  board.getTitle());
        log.info("Content : " + board.getContent());
        log.info("Writer : " + board.getWriter());
        log.info("Writer's role : " + board.getUser().getRole());
    }


    @Test
    public void testFindBoardById() {
        User user = new User();
        user.setUsername("testuser");
        user = userRepository.save(user);

        Board board = new Board();
        board.setTitle("Test Title");
        board.setContent("Test Content");
        board.setUser(user);
        board = boardRepository.save(board);

        Board foundBoard = boardRepository.findById(board.getId()).orElse(null);
        assertNotNull(foundBoard);
        assertEquals("Test Title", foundBoard.getTitle());
        assertEquals("Test Content", foundBoard.getContent());
        assertNotNull(foundBoard.getUser());
        assertEquals(user.getId(), foundBoard.getUser().getId());
    }



}
