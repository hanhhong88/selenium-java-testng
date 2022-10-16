package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browsers_Ex {
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
	public void TC_01_Url () {
		//open page techpanda
		driver.get("http://live.techpanda.org/");
		//click My Account - footer
		driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title='My Account']")).click();
		//waiting time to open page successfully
		Sleepinsecond(3);
		//verify open correct url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		//click Create My Account
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		//waiting time to open page successfully
		Sleepinsecond(3);
		//verify open correct url
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	
	}

	@Test
	public void TC_02_Titile () {
		//open page techpanda
		driver.get("http://live.techpanda.org/");
		//click My Account - footer
		driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title='My Account']")).click();
		//waiting time to open page successfully
		Sleepinsecond(3);
		//verify correct title
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		//click Create My Account
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		//waiting time to open page successfully
		Sleepinsecond(3);
		//verify correct title
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
			
	}

	@Test
	public void TC_03_Navigate () {
		//open page techpanda
		driver.get("http://live.techpanda.org/");
		//click My Account - footer
		driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title='My Account']")).click();
		Sleepinsecond(3);
		
		//click Create My Account
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		Sleepinsecond(3);
		
		//verify url create account
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
		//back to My account page
		driver.navigate().back();
		Sleepinsecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		
		//forward to Create My account
		driver.navigate().forward();
		Sleepinsecond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}
	
	@Test
	public void TC_04_Page_source_HTML () {
		//open page techpanda
		driver.get("http://live.techpanda.org/");
		
		//click My Account - footer
		driver.findElement(By.xpath("//div[@class = 'footer-container']//a[@title='My Account']")).click();
		Sleepinsecond(3);
		
		//verify page source contains texts
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		//click Create My Account
		driver.findElement(By.xpath("//a[@title = 'Create an Account']")).click();
		Sleepinsecond(3);
		
		//verify page source contains texts
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	public void Sleepinsecond(long Timeinseconds) {
		try {
			Thread.sleep(Timeinseconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 1000ms = 1s
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
