package com.sportfybe.service;

import com.sportfybe.model.User;
import com.sportfybe.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User save(User product) {
        return userRepository.save(product);
    }

    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Transactional
    public void delete(User product) {
        userRepository.delete(product);
    }
    /*
    public boolean existsByUserName(String userName) {
        return userRepository.existsByProductName(userName);
    }
    */
}
