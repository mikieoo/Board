package eo.board.service;

import eo.board.dto.BoardRequest;
import eo.board.dto.UserRequest;
import eo.board.entity.Board;
import eo.board.entity.User;
import eo.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void save(UserRequest request) {
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userRepository.save(request.toEntity());
    }

    @Transactional
    public User update(Long id, UserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            user.update(request.getNickname(), request.getPicture(), bCryptPasswordEncoder.encode(request.getPassword()));
        } else {
            user.update(request.getNickname(), request.getPicture(), user.getPassword());
        }

        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
