package com.example.basic.service;

import com.example.basic.dto.JoinDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.repository.JoinRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final JoinRepo joinRepo;

    // 회원가입
    public void register(JoinDTO dto) {
        JoinEntity user = new JoinEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(java.time.LocalDate.now().toString());
        joinRepo.save(user);
    }

    // 로그인 검증
    public JoinEntity findByEmailAndPassword(String email, String password) {
        return joinRepo.findByEmailAndPassword(email, password);
    }
}
