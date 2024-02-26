Spring Boot Restful API Sample (2024.02.23)

- JDK: 1.8
- Spring Boot: 2.7.18
- JWT

## 1. API Description

| NO | API | Description | SSL | Body Type |
|----| ---- | ---- | ---- |-----------|
| 1  | **GET** /basic/api/v1/tokne/{id} | 신규 JWT 발급 | O | None |
| 2  | **GET** /basic/api/v1/users/{id} | 유저 정보 조회 | O | None      |

<hr/>

## 2. 신규 JWT 발급 API

| API Interface                    | Parameter | In/Out   | Type   | Description |
|----------------------------------|-----------|----------|--------|-------------|
| **GET** /basic/api/v1/token/{id} |           | | | |

## 3. 유저 정보 조회 API

| API Interface | Parameter | In/Out    | Type   | Description |
| ---- |-----------|-----------|--------|-------------|
| **GET** /basic/api/v1/users/{id} | token     | Header In | String | JWT Token   |

<hr/>

## 3. 결과 예시 - 성공

**200 OK** (No Body)

<hr/>

## 4. 결과 예시 - 실패

{HTTP Status Code} {ResponPhase}<br/>Json Body
<pre>
example)

400 Bad Request
{
	"message": "Fail Reason",
	"body": {
	  "timestamp": "1970-01-01T00:00:00+00:00",
	  "status": 400,
	  "error": "Bad Request",
	  "path": "/basic/api/v1/users/1234"
	}
}
</pre>
