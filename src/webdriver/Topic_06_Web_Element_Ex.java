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
	By passwordtextbox = By.cssSelector("#disable_password");
	By biographytextarea = By.cssSelector("#bio");
	By developmentchecbox = By.cssSelector("#development");
	

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

	//@Test
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

	//@Test
	public void TC_02_Enable () {
		//open page
		driver.get("https://automationfc.github.io/basic-form/index.html");	
		
		//passwordtextbox is disable
		if (driver.findElement(passwordtextbox).isEnabled()) {
			System.out.println("Password is enable");
		} else {
			System.out.println("Password is disable");
		}
		
		//Biography textarea is disable
		if (driver.findElement(biographytextarea).isEnabled()) {
			System.out.println("Bio textarea is enable");
		} else {
			System.out.println("Bio textarea is disable");
		}
		
		//Email textbox is disable
		if (driver.findElement(emailtextbox).isEnabled()) {
			System.out.println("Email textbox is enable");
		} else {
			System.out.println("Email textbox is disable");
		}
		
		
	}

	//@Test
	public void TC_03_Selected () {
		//open page
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Verify age and development are not selected 
		Assert.assertFalse(driver.findElement(AgeUnder18radio).isSelected());
		Assert.assertFalse(driver.findElement(developmentchecbox).isSelected());
		
		//Select
		driver.findElement(AgeUnder18radio).click();
		driver.findElement(developmentchecbox).click();
		Sleepinsecond(2);
		
		//Verify age and development are selected
		Assert.assertTrue(driver.findElement(AgeUnder18radio).isSelected());
		Assert.assertTrue(driver.findElement(developmentchecbox).isSelected());
		
	}
	
	

	@Test
	public void TC_04_MailChims () {
		//open page
		driver.get("https://login.mailchimp.com/signup/");
		
		//Select
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automationFC1@gmail.com");
		By passMailChimps = By.id("new_password");
		
		driver.findElement(passMailChimps).sendKeys("abc");
		Sleepinsecond(2);
		
		//Verify lowercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passMailChimps).clear();
		driver.findElement(passMailChimps).sendKeys("ABC");
		//Verify Uppercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passMailChimps).clear();
		driver.findElement(passMailChimps).sendKeys("ABC");
		Sleepinsecond(2);
		
		//Verify Uppercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passMailChimps).clear();
		driver.findElement(passMailChimps).sendKeys("123");
		Sleepinsecond(2);
		
		//Verify numbercase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passMailChimps).clear();
		driver.findElement(passMailChimps).sendKeys("@#$");
		Sleepinsecond(2);
		
		//Verify specialcase
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		driver.findElement(passMailChimps).clear();
		driver.findElement(passMailChimps).sendKeys("12345678");
		Sleepinsecond(2);
		
		//Verify number chars >=8
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char not-completed']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
		driver.findElement(passMailChimps).clear();
		driver.findElement(passMailChimps).sendKeys("123aBc@#");
		Sleepinsecond(2);
		
		//Verify full cases
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
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
		driver.quit();
	}
}
