package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Element_Ex {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	//define global argument
	By emailtextbox = By.id("mail");
	By AgeUnder18radio = By.cssSelector("#under_18");
	By educationTextarea = By.cssSelector("#edu");
	By nameUser5text = By.xpath("//h5[text()='Name: User5']");
	By passwordtextbox = By.
	

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
		
		//open page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//verify textbox or area is displayed
		if(driver.findElement(emailtextbox).isDisplayed()){
			driver.findElement(emailtextbox).sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		//text area
		if(driver.findElement(educationTextarea).isDisplayed()){
			driver.findElement(educationTextarea).sendKeys("Automation testing 2");
			System.out.println("Education area is displayed");
		} else {
			System.out.println("Education area is not displayed");
		}
		
		//radio button
		if(driver.findElement(AgeUnder18radio).isDisplayed()){
			driver.findElement(AgeUnder18radio).click();
			System.out.println("AgeUnder18 radio is displayed");
		} else {
			System.out.println("AgeUnder18 radio is not displayed");
		}
		
		//nametextUser5
		if(driver.findElement(nameUser5text).isDisplayed()) {
			System.out.println("nameUser5text is displayed");
		} else {
			System.out.println("nameUser5text is not displayed");
		}
	}


	@Test
	public void TC_02_Enable () {
		
	}

	@Test
	public void TC_03_Selected () {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
