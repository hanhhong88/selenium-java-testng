package webdriver;


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

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
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

	@Test
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
	
	@Test
	public void TC_03_Password_less_than_6_characters () {
		//open URL
		driver.get("http://live.techpanda.org");
		//Click My Account at footer
		driver.findElement(By.xpath("//div[@class = 'footer'] //a[@title = 'My Account']")).click();
		//wait to load page successfully
		Sleepinsecond(2);
		//input invalid email with password
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("automationFC@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12345");
		//sign up
		driver.findElement(By.id("send2")).click();
		
		//verify messages
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test
	public void TC_04_Incorrect_Email () {
		//open URL
		driver.get("http://live.techpanda.org");
		//Click My Account at footer
		driver.findElement(By.xpath("//div[@class = 'footer'] //a[@title = 'My Account']")).click();
		//wait to load page successfully
		Sleepinsecond(2);
		//input invalid email with password
		driver.findElement(By.id("email")).sendKeys("automationFC@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456789");
		//sign up
		driver.findElement(By.id("send2")).click();
		
		//verify messages
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
		
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
