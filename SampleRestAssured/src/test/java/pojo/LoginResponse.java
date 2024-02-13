package pojo;

public class LoginResponse {

	/*
	 json
	 {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2MzIyMTU0MGM0ZDBjNTFmNGYyMGMxMzMiLCJ1c2VyRW1haWwiOiJ2aXBpbi5kaGltYW4yMUBnbWFpbC5jb20iLCJ1c2VyTW9iaWxlIjo5MDQxMjkzNTc1LCJ1c2VyUm9sZSI6ImN1c3RvbWVyIiwiaWF0IjoxNzA2ODc1MjY2LCJleHAiOjE3Mzg0MzI4NjZ9.jE-TMMc7SA76_RxCkwsdeEk1_QJHtZdXCBJIYwvwgWs",
    "userId": "63221540c4d0c51f4f20c133",
    "message": "Login Successfully"
	}
	  
	  
	 */
	
	private String token;

	private String userId;

	private String message;

	public String getToken() {

		return token;

	}

	public void setToken(String token) {

		this.token = token;

	}

	public String getUserId() {

		return userId;

	}

	public void setUserId(String userId) {

		this.userId = userId;

	}

	public String getMessage() {

		return message;

	}

	public void setMessage(String message) {

		this.message = message;

	}

}