package com.sportfybe.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.sportfybe.model.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String createToke(Member member){
        return JWT.create().withIssuer("member")
                .withSubject(member.getEmail())
                .withClaim("id", member.getId())
                .withClaim("role", member.getRole())
                .withExpiresAt(LocalDateTime.now().plusMinutes(200).toInstant(
                        ZoneOffset.of("-03:00")))
                .sign(Algorithm.HMAC256("secreta"));

    }
    public String getSubject(String token){
        return JWT.require(Algorithm.HMAC256("secreta"))
                .withIssuer("member")
                .build().verify(token).getSubject();
    }
}
