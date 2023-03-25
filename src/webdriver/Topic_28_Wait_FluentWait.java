package webdriver;


import java.io.File;
import java.sql.Date;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_28_Wait_FluentWait {
	WebDriver driver;
	FluentWait<WebDriver> fluentWait_Driver;
	FluentWait<WebElement> fluentWait_Element;

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
		driver.manage().window().maximize();
	}

//	@Test
	public void TC_01_GetText_Equals() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start button")).click();
		fluentWait_Driver = new FluentWait<WebDriver>(driver);
		//Selenium 3.x, 4.x upward
		//Tong time cho la bao lau
		fluentWait_Driver.withTimeout(Duration.ofSeconds(15));
		//polling = tim lai sau bao nhieu s/ms
		fluentWait_Driver.pollingEvery(Duration.ofMillis(500));
		//exception = neu khong tim thay element trong qua trinh di tim thi se nem ra ngoai le. 
		//neu khong ignore thi TC se failed.
		fluentWait_Driver.ignoring(NoSuchElementException.class);
		
		fluentWait_Driver.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				String text = driver.findElement(By.cssSelector("div#finish h4")).getText();
				System.out.println("Text is " + text);
				
				return text.equals("Hello World!");
			}
			
		});
	
	}

	//@Test
	public void TC_02_Assert_Text() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start button")).click();
		fluentWait_Driver = new FluentWait<WebDriver>(driver);
		//Selenium 3.x, 4.x upward
		//Tong time cho la bao lau
		fluentWait_Driver.withTimeout(Duration.ofSeconds(15));
		//polling = tim lai sau bao nhieu s/ms
		fluentWait_Driver.pollingEvery(Duration.ofMillis(500));
		//exception = neu khong tim thay element trong qua trinh di tim thi se nem ra ngoai le. 
		//neu khong ignore thi TC se failed.
		fluentWait_Driver.ignoring(NoSuchElementException.class);
		
		//cach viet gon
//		fluentWait_Driver.withTimeout(Duration.ofSeconds(15))
//		.pollingEvery(Duration.ofMillis(500))
//		.ignoring(NoSuchElementException.class);
		
		
		String text_Assert = fluentWait_Driver.until(new Function<WebDriver, String>() {
			@Override
			public String apply(WebDriver driver) {
				return driver.findElement(By.cssSelector("div#finish h4")).getText();
			}
		});
		
		Assert.assertEquals(text_Assert, "Hello World!");
		
	}

	//@Test
	public void TC_03_Text_Is_Displayed () {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(By.cssSelector("div#start button")).click();
		
		fluentWait_Driver = new FluentWait<WebDriver>(driver);
		//Selenium 3.x, 4.x upward
		//Tong time cho la bao lau
		fluentWait_Driver.withTimeout(Duration.ofSeconds(15));
		//polling = tim lai sau bao nhieu s/ms
		fluentWait_Driver.pollingEvery(Duration.ofMillis(500));
		//exception = neu khong tim thay element trong qua trinh di tim thi se nem ra ngoai le. 
		//neu khong ignore thi TC se failed.
		fluentWait_Driver.ignoring(NoSuchElementException.class);
		
		fluentWait_Driver.until(new Function<WebDriver, Boolean>() {

			@Override
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath("//div[@id = 'finish']/h4[text() = 'Hello World!']")).isDisplayed();
			}
			
		});
	}
		
		//@Test
		public void TC_04_Timecounter() {
			driver.get("https://automationfc.github.io/fluent-wait/");
			fluentWait_Element = new FluentWait<WebElement>(driver.findElement(By.cssSelector("div#javascript_countdown_time")));
			
			fluentWait_Element.withTimeout(Duration.ofSeconds(15));
			//polling = tim lai sau bao nhieu s/ms
			fluentWait_Element.pollingEvery(Duration.ofMillis(500));
			//exception = neu khong tim thay element trong qua trinh di tim thi se nem ra ngoai le. 
			//neu khong ignore thi TC se failed.
			fluentWait_Element.ignoring(NoSuchElementException.class);
			
			boolean counter_time = fluentWait_Element.until(new Function<WebElement, Boolean>() {

				@Override
				public Boolean apply(WebElement element) {
					return element.getText().endsWith("00");
				}
				
			});
			
			Assert.assertTrue(counter_time);
			
		}
		
		@Test
		public void TC_05_Text_Is_Displayed_Used_FindElement_Function () {
			driver.get("https://automationfc.github.io/dynamic-loading/");
//			driver.findElement(By.cssSelector("div#start button")).click();
			
			find_Element(By.cssSelector("div#start button")).click();
			
			fluentWait_Driver = new FluentWait<WebDriver>(driver);
			//Selenium 3.x, 4.x upward
			//Tong time cho la bao lau
			fluentWait_Driver.withTimeout(Duration.ofSeconds(15));
			//polling = tim lai sau bao nhieu s/ms
			fluentWait_Driver.pollingEvery(Duration.ofMillis(500));
			//exception = neu khong tim thay element trong qua trinh di tim thi se nem ra ngoai le. 
			//neu khong ignore thi TC se failed.
			fluentWait_Driver.ignoring(NoSuchElementException.class);
			
			fluentWait_Driver.until(new Function<WebDriver, Boolean>() {

				@Override
				public Boolean apply(WebDriver driver) {
					return driver.findElement(By.xpath("//div[@id = 'finish']/h4[text() = 'Hello World!']")).isDisplayed();
				}
				
			});
		}
		
		
		
		
	//customise ham tim Element 
	public WebElement find_Element(By locator) {
		FluentWait<WebDriver> fluentWait_Driver = new FluentWait<WebDriver>(driver);
		
		fluentWait_Driver.withTimeout(Duration.ofSeconds(15))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);
		
		WebElement element = fluentWait_Driver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(locator);
			}
			
		});
				return element;
	}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
