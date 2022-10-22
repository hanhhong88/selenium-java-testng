package webdriver;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Ex2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random rand;
	String email_address, firstname, lastname, fullname, password;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		rand = new Random();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email_address = "automationFC" + rand.nextInt(99999) + "@gmail.com";
		firstname = "Automation";
		lastname = "FC";
		fullname = firstname + " " + lastname;
		password = "12345678";
	}

	//@Test
	public void TC_01_Empty_Email_and_Password () {
		//open URL
		driver.get("http://live.techpanda.org");
		//Click My Account at footer
		driver.findElement(By.xpath("//div[@class = 'footer']//a[@title = 'My Account']")).click();
		//wait to load page successfully
		Sleepinsecond(2);
		//sign up with blank email and password
		driver.findElement(By.id("send2")).click();
		
		//verify messages
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		
	}

	//@Test
	public void TC_02_Invalid_Email () {
		//open URL
		driver.get("http://live.techpanda.org");
		//Click My Account at footer
		driver.findElement(By.xpath("//div[@class = 'footer'] //a[@title = 'My Account']")).click();
		//wait to load page successfully
		Sleepinsecond(2);
		//input invalid email with password
		driver.findElement(By.id("email")).sendKeys("1234556@123446");
		driver.findElement(By.id("pass")).sendKeys("1234556@123446");
		//sign up
		driver.findElement(By.id("send2")).click();
		
		//verify messages
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	//@Test
	public void TC_03_Password_less_than_6_characters () {
		//open URL
		driver.get("http://live.techpanda.org");
		//Click My Account at footer
		driver.findElement(By.xpath("//div[@class = 'footer'] //a[@title = 'My Account']")).click();
		//wait to load page successfully
		Sleepinsecond(2);
		//input password less than 6 characters.
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("automationFC@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12345");
		//sign up
		driver.findElement(By.id("send2")).click();
		
		//verify messages
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	//@Test
	public void TC_04_Incorrect_Email () {
		//open URL
		driver.get("http://live.techpanda.org");
		//Click My Account at footer
		driver.findElement(By.xpath("//div[@class = 'footer'] //a[@title = 'My Account']")).click();
		//wait to load page successfully
		Sleepinsecond(2);
		//input incorrect email created by randomly with password more than 6 characters.
		driver.findElement(By.id("email")).sendKeys(email_address);
		driver.findElement(By.id("pass")).sendKeys("123456789");
		//sign up
		driver.findElement(By.id("send2")).click();
		
		//verify messages
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
		
	}
	
	@Test
	public void TC_05_Create_Account () {
		//open URL
		driver.get("http://live.techpanda.org");
		//Click My Account at footer
		driver.findElement(By.xpath("//div[@class = 'footer'] //a[@title = 'My Account']")).click();
		//wait to load page successfully
		Sleepinsecond(2);
		
		//click Create my account
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Sleepinsecond(2);
		
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.id("email_address")).sendKeys(email_address);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title ='Register']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		String contact_Info = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contact_Info);
		Assert.assertTrue(contact_Info.contains(fullname));
		Assert.assertTrue(contact_Info.contains(email_address));
		
		driver.findElement(By.xpath("//div[@class=\"account-cart-wrapper\"]//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//img[contains(@src,'logo.png')]")).isDisplayed());
	}
	
	@Test
	public void TC_06_Re_Login () {
		driver.findElement(By.xpath("//div[@class = 'footer'] //a[@title = 'My Account']")).click();
		//wait to load page successfully
		Sleepinsecond(2);
		
		//Login with registered email and pass
		driver.findElement(By.cssSelector("#email")).sendKeys(email_address);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		
		//verify messages
		String contact_Info = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contact_Info);
		Assert.assertTrue(contact_Info.contains(fullname));
		Assert.assertTrue(contact_Info.contains(email_address));
	}
	
	public void Sleepinsecond(long Timeinseconds) {
		try {
			Thread.sleep(Timeinseconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 1000ms = 1s
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
