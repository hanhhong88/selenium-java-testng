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

public class Topic_22_Wait_FindElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	List<WebElement> elements;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		
		
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/reg/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_FindElement() {
		//case1 tim thay 1 element
		//apply implicit way
		//vao cai thay ngay thi khong can doi het timeout.
		//driver.findElement(By.cssSelector("input[name = 'firstname']"));
		
		//case2 tim thay nhieu hon 1
		//chi thao tac voi element dau tien
		//driver.findElement(By.cssSelector("input[type = 'text']")).sendKeys("Automation");
	
		//case3 khong tim thay element nao
		//tim lai sau moi 0.5s
		//neu tim thay thi giong case1 & 2, khong can cho het timeout
		//neu het timeout ma van khong thay thi se danh fail TC
		//dua ra thong bao NoException No such Element
		driver.findElement(By.xpath("//div[text() = \"What's your name?\"]"));
		
	}

	@Test
	public void TC_02_FindElements() {
		//case1 tim thay 1 element
		//apply implicit way
		//vao cai thay ngay thi khong can doi het timeout.
		//elements = driver.findElements(By.cssSelector("input[name = 'firstname']"));
		//lay ra so element tim duoc
		//System.out.println("Tim thay 1 element = " + elements.size());
		
		//case2 tim thay nhieu hon 1
		//lay ra het tat ca cac element duoc tim thay
		//elements = driver.findElements(By.cssSelector("input[type = 'text']"));
		//System.out.println("Tim thay element = " + elements.size());
		
		//case3 khong tim thay element nao
		//tim lai sau moi 0.5s
		//neu tim thay thi giong case1 & 2, khong can cho het timeout
		//neu het timeout ma van khong thay thi KHONG danh fail TC.
		// khong show exception nao het
		//tra ve list = 0
		elements = driver.findElements(By.xpath("//div[text() = \"What's your name?\"]"));
		System.out.println("Tim thay element = " + elements.size());
		Assert.assertEquals(elements.size(), 0);
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
