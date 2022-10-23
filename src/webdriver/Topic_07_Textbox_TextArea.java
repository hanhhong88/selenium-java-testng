package webdriver;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Random ran = new Random();
	//convert int arg to string 
	String EmployeeID = String.valueOf(ran.nextInt(99999));
	String PassportNum = "40517-402-96-7202";
	String Comment = "This is data test of Passport\n number and related infomation";

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
	public void TC_01_Create_New_Employee () {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//login with admin account
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		Sleepinsecond(5);
		//Go to Add Employee page
		driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
		Sleepinsecond(5);
		//Input first & last name
		driver.findElement(By.name("firstName")).sendKeys("Automationhanh");
		driver.findElement(By.name("lastName")).sendKeys("FC");
		//bc this site doesn't allow to apply .clear so must to do as below.
		WebElement employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"));
		employeeID.sendKeys(Keys.chord(Keys.COMMAND, "a"));
		employeeID.sendKeys(Keys.DELETE);
		employeeID.sendKeys(EmployeeID);
		
		driver.findElement(By.xpath("//p[text()='Create Login Details']/parent::div//span")).click();
		Sleepinsecond(5);
		driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys("afc_hanh" + EmployeeID);
		driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys("Password123!!!");
		
		//use contains(string().,'Save') here bc there is another layer
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		Sleepinsecond(8);
		
		//verify created new employee
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automationhanh");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "FC");
		Assert.assertEquals((driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value")), EmployeeID);
		
		//click Immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		Sleepinsecond(5);
		//click Add
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();
		
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(PassportNum);
		driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(Comment);
		driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
		Sleepinsecond(6);
		
		//click Edit button
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		Sleepinsecond(5);
		
		//Verify value
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), PassportNum);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), Comment);
		
		//logout
		driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Sleepinsecond(5);
		
		//re login with created account
		driver.findElement(By.name("username")).sendKeys("afc_hanh" + EmployeeID);
		driver.findElement(By.name("password")).sendKeys("Password123!!!");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
		Sleepinsecond(5);
		
		//verify info at Personal Detail page
		Assert.assertEquals(driver.findElement(By.name("firstName")).getAttribute("value"), "Automationhanh");
		Assert.assertEquals(driver.findElement(By.name("lastName")).getAttribute("value"), "FC");
		Assert.assertEquals((driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value")), EmployeeID);
		
		//click Immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		Sleepinsecond(4);
		
		//Verify data at immigration page
		driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
		Sleepinsecond(5);
		
		//Verify value
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), PassportNum);
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), Comment);
	
	}

	//@Test
	public void TC_02_Verify_New_Employee () {
		
	}

	//@Test
	public void TC_03 () {
		
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
		//driver.quit();
	}
}
