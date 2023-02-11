package webdriver;

import java.util.List;
import java.util.Random;
import java.util.Set;
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
import org.openqa.selenium.remote.server.handler.SwitchToWindow;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Window_Tab {
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
	public void TC_01_Github_2_windows_Tabs() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Sleepinsecond(3);
		String Github_ID = driver.getWindowHandle();
		System.out.println("dirver_Github " + Github_ID);
		System.out.println("page title_Github " + driver.getTitle());
		
		driver.findElement(By.xpath("//a[text() = 'GOOGLE']")).click();
		Sleepinsecond(3);
		
		System.out.println("page title Github " + driver.getTitle());
		
		Swith_To_Windows_By_ID(Github_ID);
		
		System.out.println("page title Google " + driver.getTitle());
		String Google_ID = driver.getWindowHandle();
		System.out.println("dirver_Google " + Google_ID);
		
		Swith_To_Windows_By_ID(Google_ID);
		System.out.println("page title_Github " + driver.getTitle());
	}
	
	//@Test
	public void TC_02_Switch_More_Than_2_window_Tab(){
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Sleepinsecond(3);
		String Github_ID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text() = 'GOOGLE']")).click();
		Sleepinsecond(3);
		
		Swith_To_Windows_By_Title("Google");
		System.out.println("Google title " + driver.getTitle());
		
		driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys(Keys.ENTER);
		Sleepinsecond(2);
		
		Swith_To_Windows_By_Title("Selenium WebDriver");
		System.out.println("Github title " + driver.getTitle());
		
		driver.findElement(By.xpath("//a[text() = 'FACEBOOK']")).click();
		Sleepinsecond(3);
		Swith_To_Windows_By_Title("Facebook – log in or sign up");
		System.out.println("Facebook title " + driver.getTitle());
		
		driver.findElement(By.xpath("//input[@id = 'email']")).sendKeys("Automation");
		driver.findElement(By.xpath("//input[@id = 'pass']")).sendKeys("1234567@#$");
		Sleepinsecond(2);
		
		Swith_To_Windows_By_Title("Selenium WebDriver");
		System.out.println("Github title " + driver.getTitle());
		
		driver.findElement(By.xpath("//a[text() = 'TIKI']")).click();
		Swith_To_Windows_By_Title("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		System.out.println("TIKI title " + driver.getTitle());
		
		Close_All_Windows_Tabs(Github_ID);
	}
	
	//@Test
		public void TC_03_Compare_Products(){
			driver.get("http://live.techpanda.org/");
			Sleepinsecond(2);
			
			driver.findElement(By.xpath("//a[text() = 'Mobile']")).click();
			Sleepinsecond(10);
			
			driver.findElement(By.xpath("//a[text() = 'Samsung Galaxy']/parent::h2/following-sibling::div[@class = 'actions']//a[text() = 'Add to Compare']")).click();
			Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'success-msg']//span[text() = 'The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
			
			driver.findElement(By.xpath("//a[text() = 'Sony Xperia']/parent::h2/following-sibling::div[@class = 'actions']//a[text() = 'Add to Compare']")).click();
			Assert.assertTrue(driver.findElement(By.xpath("//li[@class = 'success-msg']//span[text() = 'The product Sony Xperia has been added to comparison list.']")).isDisplayed());
			
			driver.findElement(By.xpath("//button[@title = 'Compare']")).click();
			Swith_To_Windows_By_Title("Products Comparison List - Magento Commerce");
			System.out.println("Compare page " + driver.getTitle());
			
			driver.findElement(By.xpath("//button[@class = 'button']")).click();
			Sleepinsecond(1);
			
			Swith_To_Windows_By_Title("Mobile");
			System.out.println("Mobile page " + driver.getTitle());	
		}
	
	@Test
		public void TC_04_Dictionary(){
			driver.get("https://dictionary.cambridge.org/vi/");
			Sleepinsecond(2);
			String parent_ID = driver.getWindowHandle();
			
			driver.findElement(By.xpath("//div[@class = 'hdn hdib-s']//span[text() = 'Đăng nhập']")).click();
			Sleepinsecond(2);
			
			Swith_To_Windows_By_ID(parent_ID);
			driver.findElement(By.xpath("//input[@value = 'Log in']")).click();
			Sleepinsecond(2);
			String current_ID = driver.getWindowHandle();
			Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder = 'Email *']//parent::div//span[text() = 'This field is required']")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.xpath("//input[@placeholder = 'Password *']//parent::div//span[text() = 'This field is required']")).isDisplayed());
			driver.close();
			
			Swith_To_Windows_By_ID(current_ID);
			System.out.println("parent page " + driver.getTitle());
			driver.findElement(By.xpath("//input[@name = 'q']")).sendKeys("Automation");
			driver.findElement(By.xpath("//button[@title = 'Tìm kiếm']")).click();
			Sleepinsecond(3);
			
			
		}
	
	

	public void Sleepinsecond(long Timeinseconds) {
		try {
			Thread.sleep(Timeinseconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}// 1000ms = 1s
	
	public void Swith_To_Windows_By_ID(String window_ID) {
		Set<String> all_ID_windows = driver.getWindowHandles();
		
		for(String ID : all_ID_windows) {
			if(!ID.equals(window_ID)) {
				driver.switchTo().window(ID);		
				Sleepinsecond(1);
			}
		}
	}
	
	public void Swith_To_Windows_By_Title(String page_Title) {
		Set<String> all_ID_windows = driver.getWindowHandles();
		
		for(String ID : all_ID_windows) {
			driver.switchTo().window(ID);		
			Sleepinsecond(1);
			String actual_Page_Title = driver.getTitle();
			if(actual_Page_Title.equals(page_Title)) {
				break;
			}
		}
	}
	
	public void Close_All_Windows_Tabs(String parent_ID) {
		Set<String> all_ID_windows = driver.getWindowHandles();
		
		for(String ID_3 : all_ID_windows) {
			if(!ID_3.equals(parent_ID))	{
			driver.switchTo().window(ID_3);
			driver.close();
			}
		}
		driver.switchTo().window(parent_ID);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
}
