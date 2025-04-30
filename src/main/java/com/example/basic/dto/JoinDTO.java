package com.example.basic.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JoinDTO {
    private String name;       // 회원가입 이름
    private String email;      // 이메일
    private String password;   // 비밀번호
}