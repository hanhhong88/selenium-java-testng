package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Action {
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
		
		driver = new FirefoxDriver();
		action = new Actions(driver);
		explicitWay = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_ToolTip() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		Sleepinsecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");

	}
	
	//@Test
	public void TC_02_MynTra_Shopping() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class = 'desktop-navLink']//a[text() = 'Kids']"))).perform();
		Sleepinsecond(3);
		
		driver.findElement(By.xpath("//div[@class = 'desktop-navLink']//a[text() = 'Home & Bath']")).click();
		Assert.assertEquals(driver.findElements(By.cssSelector("span.breadcrumbs-crumb")), "Kids Home Bath");
		
	}
	
	@Test
	public void TC_03_Fahasha_Shopping() {
		driver.get("https://www.fahasa.com/");
		//wait to manually close pop up bc currently haven't learn to handle it
		Sleepinsecond(10);
		//1st hover
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		Sleepinsecond(3);
		// 2nd hover
		action.moveToElement(driver.findElement(By.xpath("//span[text() = 'Sách Trong Nước']"))).perform();
		//click 1 option
		driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text() = 'Quản Trị - Lãnh Đạo']")).click();
		//Verify 
		Assert.assertTrue((driver.findElement(By.xpath("//ol[@class ='breadcrumb']//strong[text() = 'Quản Trị - Lãnh Đạo']"))).isDisplayed());
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
