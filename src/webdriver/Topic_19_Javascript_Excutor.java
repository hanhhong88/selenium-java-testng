package webdriver;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Javascript_Excutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	JavascriptExecutor jsExecutor;
	Random rand = new Random();
	String emailAddress = "testing" + String.valueOf(rand.nextInt(99999)) + "@gmail.com";
	
	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Panda() {
		navigateToUrlByJS("http://live.techpanda.org/");
		Sleepinsecond(2);
	
		Assert.assertEquals(executeForBrowser("return document.domain;"), "live.techpanda.org");
		
		Assert.assertEquals(executeForBrowser("return document.URL;"), "http://live.techpanda.org/");
		
		hightlightElement("//a[text() = 'Mobile']");
		clickToElementByJS("//a[text() = 'Mobile']");
		Sleepinsecond(2);
		
		hightlightElement("//a[text() = 'Samsung Galaxy']/parent::*/following-sibling::div[@class = 'actions']/button");
		clickToElementByJS("//a[text() = 'Samsung Galaxy']/parent::*/following-sibling::div[@class = 'actions']/button");
		Sleepinsecond(2);
		
		Assert.assertTrue(getInnerText().contains("Samsung Galaxy was added to your shopping cart."));
		
		hightlightElement("//a[text() = 'Customer Service']");
		clickToElementByJS("//a[text() = 'Customer Service']");
		Sleepinsecond(2);
		
		Assert.assertEquals(executeForBrowser("return document.title;"), "Customer Service");
		
		hightlightElement("//input[@id = 'newsletter']");
		scrollToElementOnDown("//input[@id = 'newsletter']");
		Sleepinsecond(2);
		
		sendkeyToElementByJS("//input[@id = 'newsletter']", emailAddress);
		
		hightlightElement("//button[@title = 'Subscribe']");
		clickToElementByJS("//button[@title = 'Subscribe']");
		Sleepinsecond(2);
		
		Assert.assertTrue(getInnerText().contains("Thank you for your subscription."));
		
		navigateToUrlByJS("https://demo.guru99.com/v4/");
		Sleepinsecond(2);
		
		Assert.assertEquals(executeForBrowser("return document.domain;"), "demo.guru99.com");
	}

	//@Test
	public void TC_02_Rode () {
		driver.get("https://warranty.rode.com/");
		By registorButton = By.xpath("//button[contains(text(), \"Register\")]");
		driver.findElement(registorButton).click();
		Sleepinsecond(3);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'firstname']"), "Please fill out this field.");
		getElement("//input[@id = 'firstname']").sendKeys("Automation");
		
		driver.findElement(registorButton).click();
		Sleepinsecond(3);
		
		Assert.assertEquals(getElementValidationMessage("//input[@id = 'surname']"), "Please fill out this field.");
		getElement("//input[@id = 'surname']").sendKeys("FC");
		
		driver.findElement(registorButton).click();
		Sleepinsecond(3);
		
		Assert.assertEquals(getElementValidationMessage("//div[contains(text() ,'Register')]/parent::div//input[@id = 'email']"), "Please fill out this field.");
		getElement("//div[contains(text() ,'Register')]/parent::div//input[@id = 'email']").sendKeys(emailAddress);
		
		
		driver.findElement(registorButton).click();
		Sleepinsecond(3);
		
		Assert.assertEquals(getElementValidationMessage("//div[contains(text() ,'Register')]/parent::div//input[@id = 'password']"), "Please fill out this field.");
		getElement("//div[contains(text() ,'Register')]/parent::div//input[@id = 'password']").sendKeys("123456");
		
		driver.findElement(registorButton).click();
		Sleepinsecond(3);
		
		Assert.assertEquals(getElementValidationMessage("//div[contains(text() ,'Register')]/parent::div//input[@id = 'password-confirm']"), "Please fill out this field.");
		
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
	
	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		Sleepinsecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}
