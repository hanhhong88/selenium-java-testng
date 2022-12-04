package webdriver;


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

public class Topic_10_Checkbox_RadioButton {
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
		
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		// Login Page Url matching
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		By loginButton = By.cssSelector("button.fhs-btn-login");
		//Verify login button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		
		//verify background color of login button
		String loginButtonBackGround = driver.findElement(loginButton).getCssValue("background-color");
		System.out.println(loginButtonBackGround);
	}

	//@Test
	public void TC_02_Default_Checkbox_Radiobuton_Single() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		//click 1 checkbox
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		//click 1 radio button
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		
		//verify checkbox and radio button is selected
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
		
		//un click checkbox and radio button
		driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).click();
		driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).click();
		
		//verify checkbox is deselected
		Assert.assertFalse(driver.findElement(By.xpath("//label[contains(text(),'Diabetes')]/preceding-sibling::input")).isSelected());
		//verify radio button is still selected bc radio is not the same as checkbox
		Assert.assertTrue(driver.findElement(By.xpath("//label[contains(text(),\"I don't drink\")]/preceding-sibling::input")).isSelected());
		
	}

	//@Test
	public void TC_03_Default_Checkbox_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		//finding all checkboxes
		List<WebElement> checkboxesList = driver.findElements(By.cssSelector("input.form-checkbox"));
		//click all checkboxes
		for (WebElement checkbox : checkboxesList) {
			checkbox.click();
		}
	
		//verify all clicked checkboxes is selected
		for (WebElement checkbox : checkboxesList) {
			Assert.assertTrue(checkbox.isSelected());
		}
	}
	
	//@Test
	public void TC_04_Default_Checkbox_Multiple() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		//finding all checkboxes
		List<WebElement> checkboxesList = driver.findElements(By.cssSelector("input.form-checkbox"));
		//click multiple checkboxes using for
		for (WebElement checkbox : checkboxesList) {
			if (checkbox.getAttribute("value").equals("Diabetes")) {
				//click checkbox'text = Diabetes
				checkbox.click();	
				//verify clicked checkbox is selected
				Assert.assertTrue(checkbox.isSelected());
			}
		}	
	}
	
	//@Test
	public void TC_05_ExTopic09_Select_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		if (!driver.findElement(By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
		
		if (driver.findElement(By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input")).isSelected()) {
			driver.findElement(By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input")).click();
		}
		
		Assert.assertFalse(driver.findElement(By.xpath("//label[text() = 'Dual-zone air conditioning']/preceding-sibling::input")).isSelected());
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
