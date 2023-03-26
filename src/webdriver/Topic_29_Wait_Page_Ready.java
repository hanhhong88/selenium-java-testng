package webdriver;


import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.lang.model.element.Element;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_29_Wait_Page_Ready {
	WebDriver driver;
	WebDriverWait explicitWait;
	Actions action;
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
		driver.manage().window().maximize();
		action = new Actions(driver);
		
		explicitWait = new WebDriverWait(driver, 30);
		
		
	}

	//@Test
	public void TC_01_api_orangehrm_com () {
		driver.get("https://api.orangehrm.com/");
		//wait toi khi loading icon bien mat
		//vi khi do page moi duoc loaded thanh cong
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loader div.spinner")));
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#project h1")).getText(), "OrangeHRM REST API Documentation");
	}

	//@Test
	public void TC_02_Admin_NopCommerce () {
		driver.get("https://admin-demo.nopcommerce.com/login?ReturnUrl=%2FAdmin");
		driver.findElement(By.cssSelector("input.email")).clear();
		driver.findElement(By.cssSelector("input.email")).sendKeys("admin@yourstore.com");
		
		driver.findElement(By.cssSelector("input.password")).clear();
		driver.findElement(By.cssSelector("input.password")).sendKeys("admin");
		
		driver.findElement(By.cssSelector("button.login-button")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
		driver.findElement(By.xpath("//a[text() = 'Logout']")).click();
		Assert.assertTrue(isPageLoadedSuccess());
		
		Assert.assertEquals(driver.getTitle(), "Your store. Login");
		
		
	}

	@Test
	public void TC_03_Blog_Test_Project () {
		driver.get("https://blog.testproject.io/");
		action.moveToElement(driver.findElement(By.cssSelector("h1.main-heading"))).perform();
		
		Assert.assertTrue(isPageLoadedSuccess());
		
		String keyword = "Selenium";
		
		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyword);
		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class = 'main-heading' and text() = 'Search Results']")));
		Assert.assertTrue(isPageLoadedSuccess());
		
		List<WebElement> post_titles = driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for(WebElement title:post_titles) {
		Assert.assertTrue(title.getText().contains(keyword));
		}
	}
	
	
	public boolean Wait_Ajax_loading_Invisible() {
		return explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#ajaxBusy")));
		
	}
	
	public boolean isPageLoadedSuccess() {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}
	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
