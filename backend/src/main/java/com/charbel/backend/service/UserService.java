package com.charbel.backend.service;

import org.springframework.stereotype.Service;

import com.charbel.backend.entity.User;
import com.charbel.backend.repository.UserRepository;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
