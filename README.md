# Portfolio Server

프론트엔드 `portfolio_client`와 통신하는 **Spring Boot + JPA + MySQL** 기반 REST API 서버입니다.  
Render로 백엔드, Railway MySQL로 DB를 운영하며, 클라이언트는 GitHub Pages로 배포됩니다.

---

## ⚙️ Tech Stack
- **Backend**: Spring Boot (Gradle)
- **ORM**: JPA
- **DB**: MySQL (Railway Cloud)
- **Build/Run**: Gradle Wrapper, Docker
- **Deploy**: Render (Server), Railway (DB)

---

```plaintext
portfolio_server
├─ build/                 # Gradle 빌드 산출물
├─ gradle/wrapper/        # Gradle Wrapper 환경 설정
├─ src/                   # 애플리케이션 소스 코드
│  ├─ main/
│  │  ├─ java/            # Spring Boot 애플리케이션, Controller, Service, Repository, Entity
│  │  └─ resources/       # 설정 파일(application.yml 등), 정적 리소스
│  └─ test/               # 단위/통합 테스트 코드
├─ Dockerfile             # Docker 이미지 빌드 설정
├─ build.gradle           # Gradle 빌드 스크립트
├─ settings.gradle        # 프로젝트 및 모듈 설정
├─ gradlew                # Gradle Wrapper 실행 파일 (Unix)
├─ gradlew.bat            # Gradle Wrapper 실행 파일 (Windows)
├─ .gitignore             # Git 버전 관리 제외 파일 목록
└─ .gitattributes         # Git 속성 정의

---
