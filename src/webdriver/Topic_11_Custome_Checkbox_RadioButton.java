package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custome_Checkbox_RadioButton {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		By radiobutton = By.xpath("//div[text() = 'Đăng ký cho người thân']/preceding-sibling::div/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radiobutton));
		
		Assert.assertTrue(driver.findElement(radiobutton).isSelected());
	}
	

	@Test
	public void TC_02_Default_Checkbox_Radiobuton_Single() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By radiobutton = By.cssSelector("div[aria-label= 'Hà Nội']");
		By checkbox = By.cssSelector("div[aria-label= 'Quảng Ngãi']");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(radiobutton));
		Sleepinsecond(2);
		//Assert.assertTrue(driver.findElement(radiobutton).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label= 'Hà Nội'][aria-checked = 'true']")).isDisplayed());
		Assert.assertEquals(driver.findElement(radiobutton).getAttribute("aria-checked"), "true");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkbox));
		Sleepinsecond(2);
		// Assert.assertTrue(driver.findElement(checkbox).isSelected());
		Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label= 'Quảng Ngãi'][aria-checked = 'true']")).isDisplayed());
		Assert.assertEquals(driver.findElement(checkbox).getAttribute("aria-checked"), "true");
		
	}

	//@Test
	public void TC_03_Default_Checkbox_All() {
		
	}
	
	//@Test
	public void TC_04_Default_Checkbox_Multiple() {
			
	}
	
	//@Test
	public void TC_05_ExTopic09_Select_Checkbox() {
		
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
