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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Popup_Part1 {
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Popup_Fixed_InDom_Ngoaingu24h() {
		driver.get("https://ngoaingu24h.vn/");
		//Verify login dialog is un displayed.
		By Loginbutton = By.cssSelector("div#modal-login-v1 div.modal-content");
		Assert.assertFalse(driver.findElement(Loginbutton).isDisplayed());
		
		//Click login button
		driver.findElement(By.cssSelector("button.login_")).click();
		Sleepinsecond(1);
		//Verify login dialog is displayed.
		Assert.assertTrue(driver.findElement(Loginbutton).isDisplayed());
		
		//Input user name , password
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationFC");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("123456");
		
		//Click login
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		Sleepinsecond(1);
		
		//Verify inputed information
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
	}
	
	@Test
	public void TC_02_Popup_Fixed_InDom_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		//Verify login dialog is un displayed.
		By Loginbutton = By.cssSelector("div#k-popup-account-login");
		Assert.assertFalse(driver.findElement(Loginbutton).isDisplayed());
		
		//Click login button
		driver.findElement(By.cssSelector("a.login-btn")).click();
		Sleepinsecond(2);
		//Verify login dialog is displayed.
		Assert.assertTrue(driver.findElement(Loginbutton).isDisplayed());
		
		//Input user name , password
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automationFC");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		
		//Click login
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		Sleepinsecond(2);
		//Verify inputed information
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
		
		driver.findElement(By.cssSelector("button.k-popup-account-close")).click();
		
		Assert.assertFalse(driver.findElement(Loginbutton).isDisplayed());
		
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
