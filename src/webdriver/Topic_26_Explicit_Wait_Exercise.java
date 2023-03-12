package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Explicit_Wait_Exercise {
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Telerick() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		String text_Today = driver.findElement(By.cssSelector("div#ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel")).getText();
		System.out.println(text_Today);
		
		//Wait cho ngay can click duoc clickable
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text() = '9']//parent::td")));
		
		//click vao ngay muon chon
		driver.findElement(By.xpath("//a[text() = '9']//parent::td")).click();
		
		//wait cho toi khi loading icon mat di
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style = \"display:none;\")]//div[@class = \"raDiv\"]")));
		
		//wait cai ngay duoc chon duoc selected
	
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text() = '9']//parent::td[@class = \"rcSelected\"]")));
		// verified hien thi thong tin ngay da chon
		String today_Text = driver.findElement(By.cssSelector("div#ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel")).getText();
		Assert.assertEquals(today_Text, "Thursday, March 9, 2023");
	
	}

	@Test
	public void TC_02 () {
		
	}

	@Test
	public void TC_03 () {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
