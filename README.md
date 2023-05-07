# Description

> Spring Boot 를 빠르게 습득 하기 위핸 Toy 프로젝트 입니다.

## 개발 사항

- [x] API 인증 - JWT
- [x] 애플리케이션 최초 실행 할 때 관리자 계정 생성
- [x] Swagger API Documentation 작성
- [ ] 단위 테스트(Unit Test) 코드 추가
- [x] application.properties 파일을 환경에 따라 다르게 적용
- [ ] RESTful API 개발 - HATEOAS 적용
- [x] 관리자와 사용자 API 를 분리
- [x] 다국어 지원
- [x] DDD(Domain Driven Design) 개발 - 아직 개념이 완벽하지가 않습니다.

# Tech Stacks

- Java 17
- Spring Boot 2.7.*
- Postgresql
- AWS - SES, S3
- Swagger Open API 3.0

# Entity Relationship

![Entity_Relationship](/images/blog_entity_relationship.png)

# Configuration

```properties
# timezone
app.timezone=Asia/Seoul

# locale
spring.web.locale=ko

# jwt
app.jwt-secret=
app.jwt-expiration-date=

# datasource
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver

# jpa
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# aws
cloud.aws.credentials.access-key=
cloud.aws.credentials.secret-key=
cloud.aws.region.static=

# aws - s3
cloud.aws.s3.bucket=

# aws - ses
cloud.aws.ses.form.email=

# admin user
admin.user.name=
admin.user.email=
admin.user.password=

```
# API

## 관리자

### 인증

- 관리자 로그인 `(POST /api/admin/login)`
- ~~관리자 회원가입 `(POST /api/admin/register)`~~ 
- 로그인 확인 `(GET /api/admin/me)`

### 회원 관리

- 회원 등록 `(POST /api/admin/users)`
- 회원 상세보기 `(GET /api/admin/users/{userCode})`
- 회원 수정 `(PUT /api/admin/users/{userCode})`
- 회원 삭제 `(DELETE /api/admin/users/{userCode})`

