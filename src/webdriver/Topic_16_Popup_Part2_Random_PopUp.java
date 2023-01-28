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

public class Topic_16_Popup_Part2_Random_PopUp {
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
	public void TC_01_Random_InDom_javaCodeGeeks() {
		driver.get("https://www.javacodegeeks.com/");
		Sleepinsecond(15);
		
		By lepopup = By.cssSelector("div.lepopup-popup-container>div:not([style^='display:none'])");
		if(driver.findElement(lepopup).isDisplayed()) {
			driver.findElement(By.cssSelector("div.lepopup-input>input")).sendKeys(emailAddress);
			Sleepinsecond(2);
			driver.findElement(By.cssSelector("a[data-label = 'Get the Books'],[data-label='OK']")).click();
			Sleepinsecond(3);
			Assert.assertEquals(driver.findElement(By.cssSelector("div.lepopup-element-html-content>h4")).getText(),"Thank you!");
			Assert.assertTrue(driver.findElement(By.cssSelector("div.lepopup-element-html-content>p")).getText().contains("Your sign-up request was successful"));
			Sleepinsecond(15);
			
		}
		String articleName  = "Agile Testing Explained";
		driver.findElement(By.cssSelector("input#s")).sendKeys(articleName);
		driver.findElement(By.cssSelector("button.search-button")).click();
		Sleepinsecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.post-listing>article:first-child>h2>a")).getText(), articleName);
		
		
	}
	
	//@Test
	public void TC_02_Random_InDom_VNK(){
		driver.get("https://vnk.edu.vn/");
		Sleepinsecond(40);
		
		By popUp = By.cssSelector("div#tve-p-scroller");
		if(driver.findElement(popUp).isDisplayed()) {
			driver.findElement(By.cssSelector("div#tve-p-scroller div.thrv_icon")).click();
			Sleepinsecond(2);
		}
		
		driver.findElement(By.xpath("//button[text() = 'Danh sách khóa học']")).click();
		Sleepinsecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content h1")).getText(),"Lịch Khai Giảng Tháng 2 – Duy Nhất Tháng 2 Giảm 30-35% Học Phí ,Tặng 12GB Tài Liệu Full MEPF Thiết Kế ,thi Công , Vẽ Shop");
	}
	
	@Test
	public void TC_03_Random_Not_InDom() {
		driver.get("https://dehieu.vn/");
		Sleepinsecond(5);
		
		By popUp = By.cssSelector("div.popup-content");
		if(driver.findElements(popUp).size() > 0 && driver.findElements(popUp).get(0).isDisplayed()){
			driver.findElement(By.id("popup-name")).sendKeys("Hana");
			driver.findElement(By.id("popup-email")).sendKeys("Hana@gmail.com");
			driver.findElement(By.id("popup-phone")).sendKeys("0987654321");
			Sleepinsecond(2);
			
			driver.findElement(By.cssSelector("div.popup-content button#close-popup")).click();
			Sleepinsecond(2);
		}
		driver.findElement(By.xpath("//a[text() = 'Tất cả khóa học']")).click();
		Sleepinsecond(2);
		
		String courseName = "Khóa học Thiết kế và Thi công Hệ thống BMS";
		driver.findElement(By.id("search-courses")).sendKeys(courseName);
		driver.findElement(By.cssSelector("button#search-course-button")).click();
		Sleepinsecond(2);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("div.course")).size(),1);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.course-content h4")).getText(),"Khóa học Thiết kế và Thi công Hệ thống BMS");
		
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
