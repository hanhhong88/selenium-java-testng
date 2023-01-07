package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part1_Fixed_Not_InDom {
	WebDriver driver;
	WebDriverWait explicitWay;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(new FirefoxProfile());
		options.addPreference("dom.webnotifications.enabled", false);
		driver = new FirefoxDriver(options);

		action = new Actions(driver);
		explicitWay = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Popup_Fixed_Not_InDom_Tiki() {
		driver.get("https://tiki.vn/");
		//Nen khai bao bien By vi neu khai bao webelement thi driver se tim item trong khi chua co item nen se bi loi
		By loginbutton = By.cssSelector("div.ReactModal__Content");
		
		//Verify login dialog is un displayed.
		//dung findElements so nhieu de khong bi faile TC va chay tiep duoc
		Assert.assertEquals(driver.findElements(loginbutton).size(), 0);
		
		driver.findElement(By.cssSelector("div[data-view-id*='header_account_container']")).click();
		Sleepinsecond(2);
		
		//Verify that login dialog is displayed
		Assert.assertEquals(driver.findElements(loginbutton).size(), 1);
		
		//or use findElement 
		Assert.assertTrue(driver.findElement(loginbutton).isDisplayed());
		
		driver.findElement(By.cssSelector("p.login-with-email")).click();
		Sleepinsecond(2);
		
		driver.findElement(By.xpath("//button[text() = 'Đăng nhập']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text() = 'Mật khẩu không được để trống']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='error-mess' and text() = 'Email không được để trống']")).isDisplayed());
		
		driver.findElement(By.cssSelector("img.close-img")).click();
		Sleepinsecond(2);
		
		//Verify login dialog is un displayed.
		Assert.assertEquals(driver.findElements(loginbutton).size(), 0);
	}
	
	@Test
	public void TC_02_Popup_Fixed_Not_InDom_Facebook() {
		driver.get("https://www.facebook.com");
		//Verify login dialog is un displayed.
		By Signup = By.xpath("//div[text() = 'Sign Up']/parent::div/parent::div");
		
		//Verify sign up dialog is un displayed
		Assert.assertEquals(driver.findElements(Signup).size(), 0);
		
		//open Sign up dialog
		driver.findElement(By.cssSelector("a[data-testid = 'open-registration-form-button']")).click();		
		Sleepinsecond(2);
		
		//Verify Sign up form is displayed
		Assert.assertEquals(driver.findElements(Signup).size(), 1);
		
		//input data to fields
		driver.findElement(By.name("firstname")).sendKeys("automation");
		driver.findElement(By.name("lastname")).sendKeys("FC");
		driver.findElement(By.name("reg_email__")).sendKeys("098763426543");
		driver.findElement(By.name("reg_passwd__")).sendKeys("098763426543");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("18");
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("Aug");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1999");
		driver.findElement(By.xpath("//label[text() = 'Female']//following-sibling::input")).click();
		
		//Close Sign up dialog
		driver.findElement(By.xpath("//div[text() = 'Sign Up']//parent::div//preceding-sibling::img")).click();
		Sleepinsecond(2);
		
		//Verify dialog is un displayed
		Assert.assertEquals(driver.findElements(Signup).size(), 0);
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
		// driver.quit();
	}
}
