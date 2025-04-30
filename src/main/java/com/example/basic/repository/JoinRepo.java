package com.example.basic.repository;

import com.example.basic.entity.JoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepo extends JpaRepository<JoinEntity, Long> {

    // 로그인 검증용
    JoinEntity findByEmailAndPassword(String email, String password);
}
