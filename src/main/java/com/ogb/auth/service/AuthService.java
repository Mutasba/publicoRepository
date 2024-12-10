package com.ogb.auth.service;

import com.ogb.auth.entity.UserEntity;
import com.ogb.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity registerUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity authenticateUser(String email, String password) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("email not found"));
        // Directly compare the plaintext passwords
        if (password.equals(user.getPassword())) {
            return user;
        } else {
            throw new IllegalArgumentException("Invalid password");
        }
    }

    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }


    public UserEntity updateUser(UserEntity updatedUser) {
        Long id = updatedUser.getId();
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(updatedUser.getPassword());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        userRepository.delete(user);
    }
}

