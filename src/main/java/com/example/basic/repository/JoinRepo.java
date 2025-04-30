package com.example.basic.repository;

import com.example.basic.entity.JoinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRepo extends JpaRepository<JoinEntity, Long> {

    // 로그인 검증용
    JoinEntity findByEmailAndPassword(String email, String password);

    // (선택) 이메일 중복 검증용
    boolean existsByEmail(String email);

    // (선택) 사용자 단건 조회용
    JoinEntity findByEmail(String email);
}
