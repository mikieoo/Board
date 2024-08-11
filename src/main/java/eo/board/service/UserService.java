package eo.board.service;

import eo.board.dto.UserRequest;
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
    public Long save(UserRequest request) {
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        return userRepository.save(request.toEntity()).getId();
    }

}
