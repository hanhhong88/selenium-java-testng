package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_Locator {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//mo trang web len
		driver.get("https://www.nopcommerce.com/en/register");
	}

	@Test
	public void TC_01_ID() {
		//tim element muon thao tac bang findElement.
		// tim theo: css / xpath / id / class/... (phai la duy nhat)
		// tim thay thi action len element do: click / sendkey
		driver.findElement(By.id("FirstName")).sendKeys("hanhhong");
	}

	@Test
	public void TC_02_Class () {
		// mo trang search len
		driver.get("https://demo.nopcommerce.com/search");
		//tim theo class duy nhat cua o search box va nhap text can tim
		driver.findElement(By.className("search-text")).sendKeys("laptop");
	}

	@Test
	public void TC_03_Name () {
		driver.findElement(By.name("advs")).click();
		
	}
	
	@Test
	public void TC_04_TagName() {
		System.out.print(driver.findElement(By.tagName("input")).getSize());
		
	}

	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Addresses")).click();
	}

	@Test
	public void TC_06_PartialLinkText() {
		driver.findElement(By.partialLinkText("vendor account")).click();
	}
	
	@Test
	public void TC_07_Css() {
		//mo lai trang register 
		driver.get("https://www.nopcommerce.com/en/register");
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Hana");
		driver.findElement(By.cssSelector("input[id='LastName']")).sendKeys("Nguyen");
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("hanh.nguyenthihong88@gmail.com");
	}

	@Test
	public void TC_08_Xpath() {
		//mo lai trang register 
		driver.get("https://www.nopcommerce.com/en/register");
		driver.findElement(By.xpath("//input[@data-val-required='First name is required.']")).sendKeys("Hana");		
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Nguyen");
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("hanh.nguyenthihong88@gmail.com");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
