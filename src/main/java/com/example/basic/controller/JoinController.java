package com.example.basic.controller;

import com.example.basic.dto.JoinDTO;
import com.example.basic.dto.LoginDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.service.JoinService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class JoinController {

    private final JoinService joinService;

    // 회원가입 (signup.js)
    @PostMapping
    public Map<String, Object> register(@RequestBody JoinDTO formDTO) {
        joinService.register(formDTO);
        return Map.of(
                "success", true,
                "message", "회원가입 성공"
        );
    }

    // 로그인 (login.js)
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginDTO dto) {
        JoinEntity user = joinService.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        Map<String, Object> res = new HashMap<>();
        if (user != null) {
            res.put("success", true);
            res.put("message", "로그인 성공");
            res.put("user", Map.of(
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "createdAt", user.getCreatedAt()
            ));
        } else {
            res.put("success", false);
            res.put("message", "이메일 또는 비밀번호가 틀렸습니다.");
        }

        return res;
    }

    // 로그인된 사용자 정보 (dashboard.js)
    @GetMapping("/me")
    public Map<String, Object> getUserInfo(HttpSession session) {
        Map<String, Object> res = new HashMap<>();
        Object loginUser = session.getAttribute("loginUser");

        if (loginUser instanceof JoinEntity user) {
            res.put("success", true);
            res.put("message", "사용자 정보");
            res.put("data", Map.of(
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "createdAt", user.getCreatedAt()
            ));
        } else {
            res.put("success", false);
            res.put("message", "로그인이 필요합니다.");
        }

        return res;
    }

    // 로그아웃
    @GetMapping("/logout")
    public Map<String, Object> logout(HttpSession session) {
        session.invalidate();
        return Map.of(
                "success", true,
                "message", "로그아웃 성공"
        );
    }
}
