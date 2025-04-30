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
        // 🔒 이메일 중복 방지
        if (joinRepo.findByEmailAndPassword(dto.getEmail(), dto.getPassword()) != null) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        JoinEntity user = new JoinEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(java.time.LocalDate.now().toString());

        JoinEntity saved = joinRepo.save(user);

        // 저장 확인 로그
        System.out.println("사용자 저장 완료 - ID: " + saved.getId() + ", 이메일: " + saved.getEmail());
    }

    // 로그인 검증
    public JoinEntity findByEmailAndPassword(String email, String password) {
        return joinRepo.findByEmailAndPassword(email, password);
    }
}
