package com.example.basic.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
    private String email;      // 로그인용 이메일
    private String password;   // 로그인용 비밀번호
}