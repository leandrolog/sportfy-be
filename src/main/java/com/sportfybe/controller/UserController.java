package com.api.pa.controllers;

import com.sportfybe.appExeptions.ConflictException;
import com.sportfybe.model.Match;
import com.sportfybe.model.User;
import com.sportfybe.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/user")
    public User saveUser(@RequestBody User user) {
        var userModel = new User();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        BeanUtils.copyProperties(user, userModel);
        return userService.save(userModel);
    }

    @GetMapping("/users")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers(@SortDefault(sort = "id", direction = Sort.Direction.ASC) Sort sort) {
        return userService.findAll(sort);
    }

    @GetMapping("user/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public User getUser(@PathVariable("id") Long id) {
        Optional<User> userModelOptional = userService.findById(id);
        if (userModelOptional.isEmpty()) {
            throw new ConflictException("No user with this ID was found. Please provide a valid user ID.");
        } else {
            return userModelOptional.get();
        }
    }

    @DeleteMapping("user/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Long id) {
        Optional<User> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No match was found with the provided ID.");
        }
        userService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado");
    }

    @PutMapping("user/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public User updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> userModelOptional = userService.findById(id);
        if (!userModelOptional.isPresent()) {
            throw new ConflictException("Match not found.");
        }
        User userToUpDate = userModelOptional.get();
        BeanUtils.copyProperties(user, userToUpDate);
        return userService.save(userToUpDate);
    }
}
