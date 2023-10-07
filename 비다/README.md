# 비다/신희민





## 1. 요구사항 분석



**서비스 : 게시판 application **







### 기능 목록 

#### 회원 기능 

회원 가입 

회원 목록 

회원 검색 

회원 정보 수정 

회원 삭제 



#### 게시판 기능 

게시글 작성 

게시글 목록 

게시글 검색 

게시글 수정

게시글 삭제 



### 



## 2. 도메인 설계 



### 도메인 모델 설계

![1](https://github.com/5th-UMC-HUFS/umc-5th-spring/assets/132312673/93a920f4-9afe-424d-a228-7e0d00e65d01)

### 테이블 설계 

![2](https://github.com/5th-UMC-HUFS/umc-5th-spring/assets/132312673/40f72e74-4ffa-45d7-99d9-67066cf58198)

 











## 3. 어플리케이션 아키텍처 설계 



### 프로젝트 스펙 결정 및 생성 (세팅)

* build.gradle

```bash
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.0.11'
    id 'io.spring.dependency-management' version '1.1.3'
}

group = 'com.example'
version = '0.0.1-SNAPSHOT'

java {
    sourceCompatibility = '17'
}

configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-devtools'

    implementation 'com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.9.0' // 3.0 ~



    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'com.h2database:h2'

    testImplementation 'junit:junit:4.13.1'  // test(junit4) 인식 못함 --> 이거 의존 추가해주면됨
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
test{
    useJUnitPlatform()
}
```

```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/umcProject
    username: sa
    password:
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        format_sql: true

logging:
  level:
    org.hibernate.sql: debug
    org.hibernate.orm.jdbc.bind: trace  # 3.0


```

ㄴ application.yml 

=> 의존, 매핑 등 개발환경 테스트까지 완료 

![image-20231002161451507](D:\Programming\Spring Boot\Umc\umc-5th-spring\images\README\image-20231002161451507.png)

ㄴ h2 



* test 목록 

1. 어플리케이션 동작 확인 : 기본 test / 브라우저에 포트타고 에러페이지 띄우기 
2. lombok test 
3. view 환경설정 : thymeleaf
4. h2 설치 및 접속 
5. h2 db에 jpa 연결 
6. 연결 테스트 및 junit 4 동작 확인 
7. 쿼리 파라미터 등 세부 의존 테스트 
