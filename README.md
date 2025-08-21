# Portfolio Server

프론트엔드 [`portfolio_client`](https://github.com/ChangMin59/portfolio_client)와 통신하는 **Spring Boot + JPA + MySQL** 기반 REST API 서버입니다.  
Render로 백엔드, Railway MySQL로 DB를 운영하며, 클라이언트는 GitHub Pages로 배포됩니다.

---

## ⚙️ Tech Stack
- **Backend**: Spring Boot (Gradle)
- **ORM**: JPA
- **DB**: MySQL (Railway Cloud)
- **Build/Run**: Gradle Wrapper, Docker
- **Deploy**: Render (Server), Railway (DB)

---

## 📂 프로젝트 구조

### 🔑 주요 디렉토리

- **build/** : Gradle 빌드 산출물  
- **gradle/wrapper/** : Gradle Wrapper 환경 설정  
- **src/main/java/** : Spring Boot 애플리케이션 패키지  
  - **controller/** 🎯 REST API 엔드포인트 (회원가입, 로그인, 대시보드)  
  - **service/** ⚙️ 비즈니스 로직 계층 (회원 관리, 인증 처리)  
  - **repository/** 💾 JPA Repository (DB 접근 계층)  
  - **entity/** 🗂 엔티티 클래스 (User 등 DB 매핑)  
  - **dto/** 📬 요청/응답 DTO  
- **src/main/resources/** : 설정 파일(`application.yml`), 정적 리소스  
- **src/test/** : 단위/통합 테스트 코드  
- **Dockerfile** : Docker 이미지 빌드 설정  
- **build.gradle** : Gradle 빌드 스크립트  
- **settings.gradle** : 프로젝트 및 모듈 설정  

---

## 📌 주요 기능

- **회원가입 API**
  - 이름, 이메일, 비밀번호 입력값 유효성 검사
  - DB 저장 후 사용자 엔티티 생성

- **로그인 API**
  - 이메일/비밀번호 인증 처리
  - 성공 시 JWT 또는 세션 기반 인증 정보 반환

- **사용자 대시보드 API**
  - 로그인한 사용자 정보(이름, 이메일, 가입일) 조회
  - 클라이언트 대시보드에 표시

- **로그아웃 API**
  - 인증 세션 해제
  - 클라이언트에서 로그인 페이지로 리다이렉트

  ---
 
  ## 📡 API 엔드포인트

- **POST** `/api/auth/signup` ✍️ 회원가입  
  - 이름, 이메일, 비밀번호 입력값을 받아 DB에 사용자 등록  
  - 유효성 검사 실패 시 `400 Bad Request` 응답  

- **POST** `/api/auth/login` 🔑 로그인  
  - 이메일/비밀번호 인증  
  - 성공 시 사용자 정보 + JWT 토큰 반환  
  - 실패 시 `401 Unauthorized` 응답  

- **GET** `/api/users/me` 👤 현재 사용자 정보 조회  
  - 로그인된 사용자 이름, 이메일, 가입일 반환  
  - 인증 토큰 필요 (헤더 Authorization)  

- **POST** `/api/auth/logout` 🚪 로그아웃  
  - 사용자 세션 또는 토큰 무효화  
  - 클라이언트는 로그인 페이지로 리다이렉트 처리

  ---
 
### 📨 요청/응답 예시

- **회원가입 (Signup)** ✍️  
  - **요청**
    ```json
    POST /api/auth/signup
    {
      "name": "홍길동",
      "email": "test@example.com",
      "password": "1234"
    }
    ```
  - **응답**
    ```json
    {
      "id": 1,
      "name": "홍길동",
      "email": "test@example.com",
      "joinedAt": "2025-08-01T10:21:00Z"
    }
    ```

---

- **로그인 (Login)** 🔑  
  - **요청**
    ```json
    POST /api/auth/login
    {
      "email": "test@example.com",
      "password": "1234"
    }
    ```
  - **응답**
    ```json
    {
      "id": 1,
      "name": "홍길동",
      "email": "test@example.com",
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."
    }
    ```

---

- **현재 사용자 조회 (Me)** 👤  
  - **요청**
    ```
    GET /api/users/me
    Authorization: Bearer <JWT 토큰>
    ```
  - **응답**
    ```json
    {
      "id": 1,
      "name": "홍길동",
      "email": "test@example.com",
      "joinedAt": "2025-08-01T10:21:00Z"
    }
    ```

---

- **로그아웃 (Logout)** 🚪  
  - **요청**
    ```
    POST /api/auth/logout
    Authorization: Bearer <JWT 토큰>
    ```
  - **응답**
    ```json
    {
      "message": "로그아웃 되었습니다."
    }
    ```
    
---
