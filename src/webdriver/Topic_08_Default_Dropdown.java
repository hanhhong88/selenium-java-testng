package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Default_Dropdown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, emailAddress, companyName, password, day, month, year, country, state, city, address, zip, phonenumber;

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

		firstName = "Hana";
		lastName = "Nguyen";
		emailAddress = "heobavi" + getRandomNum() + "@gmail.com";
		companyName = "Vibbidi";
		password = "12345678";
		day = "8";
		month = "September";
		year = "1994";
		
		country = "United States";
		state = "Florida";
		city = "Miami";
		address = "123 PO";
		zip = "33101";
		phonenumber = "+13055555521";

	}

	@Test
	public void TC_01_Add_New_Account() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText(day);
		new Select(driver.findElement(By.name("DateOfBirthMonth"))).selectByVisibleText(month);
		new Select(driver.findElement(By.name("DateOfBirthYear"))).selectByVisibleText(year);

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		driver.findElement(By.id("register-button")).click();

		// verify created successfully account
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

		// click My account
		driver.findElement(By.cssSelector("a.ico-account")).click();
		// Verify info
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay"))).getFirstSelectedOption().getText(), day);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(), month);
		Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(), year);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);

	}

	@Test
	public void TC_02_Add_New_Address() {
		driver.findElement(By.cssSelector("li.customer-addresses>a")).click();
		driver.findElement(By.xpath("//button[@class = \"button-1 add-address-button\"]")).click();
		driver.findElement(By.id("Address_FirstName")).sendKeys(firstName);
		driver.findElement(By.id("Address_LastName")).sendKeys(lastName);
		driver.findElement(By.id("Address_Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Address_Company")).sendKeys(companyName);
		new Select(driver.findElement(By.id("Address_CountryId"))).selectByVisibleText(country);
		new Select(driver.findElement(By.id("Address_StateProvinceId"))).selectByVisibleText(state);
		driver.findElement(By.id("Address_City")).sendKeys(city);
		driver.findElement(By.id("Address_Address1")).sendKeys(address);
		driver.findElement(By.id("Address_ZipPostalCode")).sendKeys(zip);
		driver.findElement(By.id("Address_PhoneNumber")).sendKeys(phonenumber);
		driver.findElement(By.cssSelector("button.save-address-button")).click();
		
		//verify info added
		Assert.assertEquals(driver.findElement(By.cssSelector("li.name")).getText(), firstName + " " + lastName );
		Assert.assertTrue(driver.findElement(By.cssSelector("li.email")).getText().contains(emailAddress));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.phone")).getText().contains(phonenumber));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.company")).getText(), companyName);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.address1")).getText(), address);
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(city));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(state));
		Assert.assertTrue(driver.findElement(By.cssSelector("li.city-state-zip")).getText().contains(zip));
		Assert.assertEquals(driver.findElement(By.cssSelector("li.country")).getText(), country);
		
	}

	// random number
	public int getRandomNum() {
		Random ran = new Random();
		return ran.nextInt(99999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
