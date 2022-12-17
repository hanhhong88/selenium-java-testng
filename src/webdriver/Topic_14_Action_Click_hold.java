package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_Click_hold {
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

	@Test
	public void TC_01_Click_and_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// find and save items in to a list
		List<WebElement> list_Number = driver.findElements(By.cssSelector("ol#selectable>li"));
		// click and hold mouse 1st item
		action.clickAndHold(list_Number.get(0))
				// move to another items
				.moveToElement(list_Number.get(7))
				// nha chuot trai
				.release()
				// excute
				.perform();
		Sleepinsecond(3);

		// Verify items are selected
		// get list selected Number
		List<WebElement> list_Number_selected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		// verify number of selected numbers
		Assert.assertEquals(list_Number_selected.size(), 8);

	}

	@Test
	public void TC_02_Click_Hold_Random() {
		//verify if Windows / Mac then select Control / Command key
		Keys key = null;
		if (osName.contains("Window")) {
			key = Keys.CONTROL;
		} else {
			key = Keys.COMMAND;
		}
		driver.get("https://automationfc.github.io/jquery-selectable/");
		// find and save items in to a list
		List<WebElement> list_Number = driver.findElements(By.cssSelector("ol#selectable>li"));
		//Push Command / Control key
		action.keyDown(key);
		// click other random numbers 
		action.click(list_Number.get(0));
		action.click(list_Number.get(3));
		action.click(list_Number.get(5));
		action.click(list_Number.get(10));
		action.perform();
		//Release Command / Control key
		action.keyUp(key);
		Sleepinsecond(3);

		// Verify items are selected
		// get list selected Number
		List<WebElement> list_Number_selected_random = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		// verify number of selected numbers
		Assert.assertEquals(list_Number_selected_random.size(), 4);
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
