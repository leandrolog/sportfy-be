package com.sportfybe.controller;

import com.sportfybe.dto.LoginDto;
import com.sportfybe.model.User;
import com.sportfybe.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    private void validateLoginRequest(LoginDto login) {
        if (login.getEmail() == null || login.getPassword() == null) {
            throw new IllegalArgumentException("Email and password are required.");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto login) {
        try {
            validateLoginRequest(login);
            UsernamePasswordAuthenticationToken authRequest =
                    new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());

            Authentication authentication = this.authenticationManager
                    .authenticate(authRequest);

            User authUser = (User) authentication.getPrincipal();
            String token = tokenService.createToke(authUser);
            return ResponseEntity.ok(token);
        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.badRequest().body("Invalid input data");
        }
    }

}
