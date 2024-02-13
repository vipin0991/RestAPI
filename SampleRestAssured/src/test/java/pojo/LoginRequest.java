package pojo;

public class LoginRequest {
	
	/*
	 json format
	  {
    "userEmail": "vipin.dhiman21@gmail.com",
    "userPassword": "@Vipin123"
		}
	  
	 
	 */
	
	

	private String userEmail;

	private String userPassword;

	public String getUserEmail() {

		return userEmail;

	}

	public void setUserEmail(String userEmail) {

		this.userEmail = userEmail;

	}

	public String getUserPassword() {

		return userPassword;

	}

	public void setUserPassword(String userPassword) {

		this.userPassword = userPassword;

	}

}
