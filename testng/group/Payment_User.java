package group;

import org.testng.annotations.Test;

public class Payment_User {
	@Test(groups = "user")
	public void Register() {
		System.out.println("Register");
	}
	
	@Test(groups = "user")
	public void Login() {
		System.out.println("Login");
	}
}
