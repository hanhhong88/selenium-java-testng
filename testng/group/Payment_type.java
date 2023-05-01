package group;

import org.testng.annotations.Test;

public class Payment_type {
	@Test(groups = "pay")
	public void Visa() {
		System.out.println("Visa");
	}
	
	@Test(groups = "pay")
	public void Cheque() {
		System.out.println("Cheque");
	}
	
	@Test(groups = "pay")
	public void Credit() {
		System.out.println("Credit");
	}

}
