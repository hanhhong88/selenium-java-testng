package webdriver;


import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element {
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
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01 () { 
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		//assign to an argument to reuse in needed
		WebElement emailtextbox = driver.findElement(By.id("Email"));
		
		//or just using directly if there is 1 time using to save memory
		driver.findElement(By.id("Password")).clear();
		
		//clear data in textbox / text area / dropdown(editable)
		emailtextbox.clear();
		
		//input data to textbox / text area / dropdown(editable)
		emailtextbox.sendKeys("");
		
		//click to button, link, checkbox,...
		emailtextbox.click();
		
		
		
		
		
		
		
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
