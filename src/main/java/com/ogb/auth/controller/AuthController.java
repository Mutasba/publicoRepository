package com.ogb.auth.controller;
import com.ogb.auth.entity.UserEntity;
import com.ogb.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(@RequestBody UserEntity user) {
        UserEntity registeredUser = authService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity request) {
        UserEntity user = authService.authenticateUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/todos-usuarios")
    public List<UserEntity> allUsers(){
        return authService.getAllUser();
    }

    @PutMapping("/update")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity updatedUser) {
        UserEntity user = authService.updateUser(updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        authService.deleteUser(id);
        return ResponseEntity.ok("User with ID " + id + " was successfully deleted.");
    }

}
