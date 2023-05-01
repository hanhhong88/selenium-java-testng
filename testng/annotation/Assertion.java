package annotation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertion {
	String name = "Hello";
	boolean status = false;
	@Test
	public void Assertion() {
		Assert.assertEquals(name, "Hallo");
		Assert.assertTrue(status);
	}

}
