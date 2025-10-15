package com.groupTuAnh.service;

import com.groupTuAnh.model.User;
import com.groupTuAnh.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User authenticate(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("tài khoản không tồn tại."));

        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Mật khẩu không đúng.");
        }
        return user;
    }

}
