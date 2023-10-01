package com.sportfybe.service;

import com.sportfybe.model.Member;
import com.sportfybe.repository.MemberRepository;
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
    private MemberRepository memberRepository;


    @Transactional
    public Member save(Member product) {
        return memberRepository.save(product);
    }

    public List<Member> findAll(Sort sort) {
        return memberRepository.findAll(sort);
    }

    public Optional<Member> findById(Long userId) {
        return memberRepository.findById(userId);
    }

    @Transactional
    public void delete(Member product) {
        memberRepository.delete(product);
    }
    /*
    public boolean existsByUserName(String userName) {
        return userRepository.existsByProductName(userName);
    }
    */
}
