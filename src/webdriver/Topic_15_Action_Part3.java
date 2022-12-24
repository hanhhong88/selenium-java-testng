package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Action_Part3 {
	WebDriver driver;
	WebDriverWait explicitWay;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		explicitWay = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Double_Click_Chrome() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		action.doubleClick(driver.findElement(By.xpath("//button[text() = 'Double click me']"))).perform();
		Sleepinsecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}
	
	//@Test
	public void TC_02_Double_Click_Firefox() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//button[text() = 'Double click me']")));
		Sleepinsecond(1);
		
		action.doubleClick(driver.findElement(By.xpath("//button[text() = 'Double click me']"))).perform();
		Sleepinsecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}

	//@Test
	public void TC_03_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		Sleepinsecond(1);
		
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		Sleepinsecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		Sleepinsecond(2);
		
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		Sleepinsecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible")).isDisplayed());
		Sleepinsecond(2);
		
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
		Sleepinsecond(2);
		
		driver.switchTo().alert().accept();
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
		
	}
	
	@Test
	public void TC_04_Drag_and_drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		Sleepinsecond(2);
		
		WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		
		//Verify text
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
		//Verify colour
		String bigCircleBackground_RGB = bigCircle.getCssValue("background-color");
		System.out.println(bigCircleBackground_RGB);
		
		String bigCircleBackground_Hexa = Color.fromString(bigCircleBackground_RGB).asHex();
		System.out.println(bigCircleBackground_Hexa);
		
		bigCircleBackground_Hexa = bigCircleBackground_Hexa.toUpperCase();
		Assert.assertEquals(bigCircleBackground_Hexa, "#03A9F4");
		
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
		// driver.quit();
	}
}
