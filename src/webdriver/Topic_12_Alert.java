package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	WebDriverWait explicitWay;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		explicitWay = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text() = 'Click for JS Alert']")).click();
		Sleepinsecond(3);
		
		//wait and switch
		explicitWay.until(ExpectedConditions.alertIsPresent());
		
		//Verify text
		Assert.assertEquals(explicitWay.until(ExpectedConditions.alertIsPresent()).getText(), "I am a JS Alert");
		
		//Accept
		explicitWay.until(ExpectedConditions.alertIsPresent()).accept();
		Sleepinsecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");

	}
	
	//@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text() = 'Click for JS Confirm']")).click();
		Sleepinsecond(3);
		
		//wait and switch
		explicitWay.until(ExpectedConditions.alertIsPresent());
		
		//Verify text
		Assert.assertEquals(explicitWay.until(ExpectedConditions.alertIsPresent()).getText(), "I am a JS Confirm");
		
		//Cancel = dismiss 
		explicitWay.until(ExpectedConditions.alertIsPresent()).dismiss();
		Sleepinsecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
		
		
	}

	@Test
	public void TC_03_Promp_Alert() {
		String text3 = "Hello Hana";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text() = 'Click for JS Prompt']")).click();
		Sleepinsecond(3);
		
		//wait and switch
		explicitWay.until(ExpectedConditions.alertIsPresent());
		
		//Verify text
		Assert.assertEquals(explicitWay.until(ExpectedConditions.alertIsPresent()).getText(), "I am a JS prompt");
		
		//input text
		explicitWay.until(ExpectedConditions.alertIsPresent()).sendKeys(text3);
		Sleepinsecond(2);
		
		explicitWay.until(ExpectedConditions.alertIsPresent()).accept();
		Sleepinsecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered:" + " " + text3);
	}
	
	//@Test
	public void TC_04_Authenticate_Alert() {
		String userName = "admin";
		String passWord = "admin";
		driver.get(passUserandPasstoUrl("http://the-internet.herokuapp.com/basic_auth", userName, passWord));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());
						
		}
	
	//@Test
	//open and by pass userName and passWord to link B which is clicked from link A
	public void TC_05_Authenticate_Alert_2() {
		String userName = "admin";
		String passWord = "admin";
		//get link A
		driver.get("http://the-internet.herokuapp.com");
		
		//get URL of link B
		String basicAuthUrl = driver.findElement(By.xpath("//a[text() = 'Basic Auth']")).getAttribute("href");
		
		//pass userName and passWord when link B is opened
		driver.get(passUserandPasstoUrl(basicAuthUrl,userName, passWord));
		
		//Verify login successfully
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(), 'Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
	
	public String passUserandPasstoUrl (String url, String Username, String Password) {
	String[] arrayUrl = url.split("//");
	return arrayUrl[0] + "//" + Username + ":" + Password + "@" + arrayUrl[1];
	
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