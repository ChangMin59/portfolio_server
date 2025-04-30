package com.example.basic.controller;

import com.example.basic.dto.JoinDTO;
import com.example.basic.dto.LoginDTO;
import com.example.basic.entity.JoinEntity;
import com.example.basic.service.JoinService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final JoinService joinService;

    // 회원가입 - signup.html
    @PostMapping
    public Map<String, Object> register(@RequestBody JoinDTO formDTO) {
        joinService.register(formDTO);  // 바뀐 부분

        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("message", "회원가입 성공");
        return res;
    }

    // 로그인 - login.html
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginDTO dto, HttpSession session) {
        JoinEntity user = joinService.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        Map<String, Object> res = new HashMap<>();
        if (user != null) {
            session.setAttribute("loginUser", user);
            res.put("success", true);
            res.put("message", "로그인 성공");
        } else {
            res.put("success", false);
            res.put("message", "이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        return res;
    }

    // 내 정보 확인 - dashboard.html
    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(HttpSession session) {
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
        Map<String, Object> res = new HashMap<>();
        res.put("success", true);
        res.put("message", "로그아웃 완료");
        return res;
    }
}
