package webdriver;

import java.util.List;
import java.util.Random;
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

public class Topic_17_Frame_IFrame {
	WebDriver driver;
	WebDriverWait explicitWay;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	Actions action;
	String emailAddress = "testdemo" + getRandomnumber() + "@gmail.com"; 

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
	public void TC_01_Random_Iframe_Kyna() {
		driver.get("https://skills.kynaenglish.vn/");
		Sleepinsecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div.fanpage iframe")).isDisplayed());
		driver.switchTo().frame(0);

		Assert.assertEquals(driver.findElement(By.xpath("//a[text() = \"Kyna.vn\"]/parent::div/following-sibling::div")).getText(),"165K likes");
		
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.button_bar")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("hanh nguyen");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987654321");
		new Select (driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.xpath("//textarea[@name = 'message']")).sendKeys("test");
		
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		List <WebElement> courseList = driver.findElements(By.cssSelector("ul.k-box-card-list div.content h4"));
		for(WebElement course : courseList) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains("Excel"));
		}	
	}
	
	@Test
	public void TC_02_Frame(){
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		Sleepinsecond(5);
		
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("hanhnguyen");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		Sleepinsecond(3);
		
		driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.cssSelector("input#keyboard")).isDisplayed());
		
	}


	public void Sleepinsecond(long Timeinseconds) {
		try {
			Thread.sleep(Timeinseconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 1000ms = 1s
	
	public int getRandomnumber() {
		return new Random().nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
