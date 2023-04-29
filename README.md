# Description

> Spring Boot 를 빠르게 숙달 하기 위핸 Toy 프로젝트 입니다.
> 관리자와 사용자 API 로 분리해서 개발이 되며 관리자 API 에서는 블로그 데이터를 생성 하고 사용자는 인증 후 관리자가 생성한 블로그 글을 읽고 좋아요 및 댓글을 남기는 방식으로 구상 하였습니다.

### 개발 필수요건

#### 관리자

- [x] 인증 방식은 JWT 로 진행을 합니다.
- [x] 등록 가능한 카테고리 Depth 는 3 Depth 까지 가능 합니다.
- [x] 블로그 글을 작성 후 slug 로 블로글 조회를 합니다.

### 사용자

- [x] 회원 가입은 이메일 인증으로 진행을 합니다.
- [x] 로그인을 후 서비스 이용이 가능 합니다.
- [x] 블로그 글에 대한 좋아요, 댓글로 블로그 글에 대한 의사 표현이 가능 합니다.
- [x] 첨부 파일 다운로드가 가능 합니다.

# API

## 관리자

### 인증

- 관리자 로그인 `(POST /api/admin/login)`
- [임시] - 관리자 회원가입 `(POST /api/admin/register)`