package webdriver;


import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	String bayMau = "BayMau.jpeg";
	String LangSon = "Lang Son.jpeg";
	String QuyNhon = "Quy Nhon.jpeg";
	
	String bayMau_path = projectPath + File.separator + "uploadFile" + File.separator + bayMau;
	String LangSon_path = projectPath + File.separator + "uploadFile" + File.separator + LangSon;
	String QuyNhon_path = projectPath + File.separator + "uploadFile" + File.separator + QuyNhon;
	
	
	
	
	
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
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_OneFile_OneTime () {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type = 'file']")).sendKeys(bayMau_path);
		Sleepinsecond(2);
		driver.findElement(By.xpath("//input[@type = 'file']")).sendKeys(LangSon_path);
		Sleepinsecond(2);
		driver.findElement(By.xpath("//input[@type = 'file']")).sendKeys(QuyNhon_path);
		Sleepinsecond(2);
		
		List<WebElement> startButtons = driver.findElements(By.cssSelector("tbody.files button.start"));
		//Verify load file len 
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = 'Quy Nhon.jpeg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = 'Lang Son.jpeg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class = 'name' and text() = 'BayMau.jpeg']")).isDisplayed());
		
		//click upload cho tung file
		for (WebElement start : startButtons) {
			start.click();
			Sleepinsecond(2);
		}
		
		//verify upload file thanh cong
		
		
	}

	@Test
	public void TC_02_MultiFile_OneTime () {
		
	}

	@Test
	public void TC_03 () {
		
	}
	
	public void Sleepinsecond(long Timeinseconds) {
		try {
			Thread.sleep(Timeinseconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}// 1000ms = 1s
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
