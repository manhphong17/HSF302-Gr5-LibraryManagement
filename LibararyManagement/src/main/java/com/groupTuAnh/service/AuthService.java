package com.groupTuAnh.service;

import org.springframework.stereotype.Service;

import com.groupTuAnh.model.Account;
import com.groupTuAnh.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository userRepository;

    public Account authenticate(String username, String password) {
        Account user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("tài khoản không tồn tại."));

        if(!user.getPassword().equals(password)){
            throw new RuntimeException("Mật khẩu không đúng.");
        }
        return user;
    }

}
