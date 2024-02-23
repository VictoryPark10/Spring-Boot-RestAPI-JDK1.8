Spring Boot Restful API Sample

## [API Description]

| NO | API | Description | SSL | Body Type |
| ---- | ---- | ---- | ---- | ---- |
| 1 | **GET** /basic/api/v1/users/{id} | 유저 정보 조회 | O | None|

<hr/>

## 1. 유저 정보 조회 API

| API Interface | Parameter | In/Out | Type | Description |
| ---- | ---- | ---- | ---- | ---- |
| **GET** /basic/api/v1/users/{id} | | | | |

<hr/>

## 2. 결과 포맷

| Case | Sample |
| ---- | ---- |
| Success | **200 OK** (No Body) |
| Fail | {HTTP Status Code} {ResponPhase}
<pre>
  
  {
    "message": "Fail Reason",
    "body": {
      "timestamp": "1970-01-01T00:00:00+00:00",
      "status": 400,
      "error": "Bad Request",
      "path": "/basic/api/v1/users/1234"
    }
  }
<pre/> |
