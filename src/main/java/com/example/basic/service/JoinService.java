package com.example.basic.service;

import com.example.basic.dto.JoinDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.repository.JoinRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JoinService {
    private final JoinRepo joinRepo;

    public void register(JoinDTO dto) {
        if (joinRepo.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        JoinEntity user = new JoinEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setCreatedAt(java.time.LocalDate.now().toString());

        joinRepo.save(user);
    }

    public JoinEntity login(String email, String password) {
        return joinRepo.findByEmailAndPassword(email, password);
    }

    public JoinEntity findByEmailAndPassword(String email, String password) {
        return joinRepo.findByEmailAndPassword(email, password);
    }

    public boolean existsByEmail(String email) {
        return joinRepo.existsByEmail(email);
    }

    public JoinEntity findByEmail(String email) {
        return joinRepo.findByEmail(email);
    }
}
