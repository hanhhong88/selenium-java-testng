package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Wait_Element_Staless {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		//apply for find Element / Elements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//apply for state
		explicitWait = new WebDriverWait(driver, 30);
		
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Visible_Display() {
		driver.get("https://www.facebook.com/");
		
		//dk1: ap dung cho truong hop co trong HTML (DOM) va co hien thi tren giao dien
		//cho cho toi khi email text field hien thi
		//cho trong 30s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
	
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("hanh@gmail.com");
		
	}

	//@Test
	public void TC_02_Invisible_Undisplayed_Case1() {
		//dk2: ap dung cho truong hop khong co tren UI nhung co trong DOM
		
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid = 'open-registration-form-button']")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name = 'reg_email_confirmation__']")));
		
		driver.findElement(By.xpath("//input[@name = 'reg_email__']")).sendKeys("dao@gmail.com");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name = 'reg_email_confirmation__']")));
		
		driver.findElement(By.cssSelector("input[name = 'reg_email_confirmation__']")).sendKeys("dao@gmail.com");
	}

	//@Test
	public void TC_02_Invisible_Undisplayed_Case2() {
		//dieu kien khong co tren UI va cung khong co trong DOM
		
		driver.get("https://www.facebook.com/");
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name = 'reg_email_confirmation__']")));
		
	}
	
	
	//@Test
	public void TC_03_Presence_case1() {
		//ket hop dieu kien 1 va 2_1
		//dk1: ap dung cho truong hop co trong HTML (DOM) va co hien thi tren giao dien
		
		driver.get("https://www.facebook.com/");
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input#email")));
	}
	
	//@Test
	public void TC_03_Presence_case2() {
		//ket hop dieu kien 1 va 2_1
		//dk2: ap dung cho truong hop khong co tren UI nhung co trong DOM
		
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid = 'open-registration-form-button']")).click();
		
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name = 'reg_email_confirmation__'")));
	}
	
	@Test
	public void TC_04_Staleness() {
		driver.get("https://www.facebook.com/");
		
		driver.findElement(By.cssSelector("a[data-testid = 'open-registration-form-button']")).click();
		
		//wait xac dinh la co thanh phan can verify trang thai trong DOM 
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name = 'reg_email_confirmation__'")));
		
		//staleness bat buoc phai khai bao bien WebElement can verify trang thai
		WebElement confirm_Email_Reg = driver.findElement(By.cssSelector("input[name = 'reg_email_confirmation__'"));
		
		//close dialog sign up
		driver.findElement(By.xpath("//div[text() = 'Sign Up']/parent::div/preceding-sibling::img")).click();
		
		//verify khong con thay webElement da khai bao nua
		explicitWait.until(ExpectedConditions.stalenessOf(confirm_Email_Reg));
		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
