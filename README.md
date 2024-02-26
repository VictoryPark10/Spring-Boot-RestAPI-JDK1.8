### Spring Boot Restful API Sample (2024.02.23)

- JDK: 1.8
- WAS: Undertow 2.2.28
- Spring Boot: 2.7.18
- JWT

## 0. Index
- [API Description](#1-api-description)
- [신규 JWT 발급 API](#2-신규-jwt-발급-api)
- [유저 정보 조회 API](#3-유저-정보-조회-api)
- [신규 유저 정보 입력 API](#4-신규-유저-정보-입력-api)
- [유저 정보 수정 API](#5-유저-정보-수정-api)
- [유저 정보 삭제 API](#6-유저-정보-삭제-api)
- [결과 예시 - 성공](#7-결과-예시---성공)
- [결과 예시 - 실패](#8-결과-예시---실패)

## 1. API Description

| NO | API | Description | SSL | Body Type |
|----| ---- |-------------| ---- |-----------|
| 1  | **GET** /basic/api/v1/tokne/{id} | 신규 JWT 발급   | O | None      |
| 2  | **GET** /basic/api/v1/users/{id} | 유저 정보 조회    | O | None      |
| 3 | **POST** /basic/api/v1/users/{id} | 신규 유저 정보 입력 | O | Json      |
| 4 | **PUT** /basic/api/v1/users/{id} | 유저 정보 변경    | O | Json      |
| 5 | **DELETE** /basic/api/v1/users/{id} | 유저 정보 삭제    | O | Json |

<hr/>

## 2. 신규 JWT 발급 API

| API Interface                    | Parameter | In/Out   | Type   | Description |
|----------------------------------|-----------|----------|--------|-------------|
| **GET** /basic/api/v1/token/{id} |           | | | |

## 3. 유저 정보 조회 API

| API Interface | Parameter | In/Out    | Type   | Description |
| ---- |-----------|-----------|--------|-------------|
| **GET** /basic/api/v1/users/{id} | token     | Header In | String | JWT Token   |

## 4. 신규 유저 정보 입력 API

| API Interface                      | Parameter | In/Out    | Type   | Description |
|------------------------------------|-----------|-----------|--------|-------------|
| **POST** /basic/api/v1/users/{id} | token     | Header In | String | JWT Token   |
|                                    | password | In | String | User Password |
|                                    | name | In | String | User Name |
|                                    | age | In | Integer | User Age |

## 5. 유저 정보 수정 API

| API Interface                    | Parameter | In/Out        | Type   | Description |
|----------------------------------|-----------|---------------|--------|-------------|
| **PUT** /basic/api/v1/users/{id} | token     | Header In     | String | JWT Token   |
|                                  | password | In (Optional) | String | User Password |
|                                  | name | In (Optional) | String | User Name |
|                                  | age | In (Optional) | Integer | User Age |

## 6. 유저 정보 삭제 API

| API Interface                       | Parameter | In/Out    | Type   | Description |
|-------------------------------------|-----------|-----------|--------|-------------|
| **DELETE** /basic/api/v1/users/{id} | token     | Header In | String | JWT Token   |

<hr/>

## 7. 결과 예시 - 성공

**200 OK** (No Body)

<hr/>

## 8. 결과 예시 - 실패

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
