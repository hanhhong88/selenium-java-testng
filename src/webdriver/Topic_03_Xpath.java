package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath {
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
	public void TC_01_Empty_Data () {
		//open webpage
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//find web elements in case clicking "Dang ky" button with empty data
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//compare actual result and expected result
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_Invalid_Email () {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Action
		//input data to web element - text boxes
		driver.findElement(By.id("txtFirstname")).sendKeys("hanhhong");
		driver.findElement(By.id("txtEmail")).sendKeys("hanhhong123.ggg");
		driver.findElement(By.id("txtCEmail")).sendKeys("hanhhong123.ggg");
		driver.findElement(By.id("txtPassword")).sendKeys("12345678910");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345678910");
		driver.findElement(By.id("txtPhone")).sendKeys("09875566778");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Assert error message with expected result
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
	}
	
	@Test
	public void TC_03_Incorrect_Email () {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Action
		//input data to web element - text boxes
		driver.findElement(By.id("txtFirstname")).sendKeys("hanhhong");
		driver.findElement(By.id("txtEmail")).sendKeys("hanhhong@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hanhhong@gmail.net");
		driver.findElement(By.id("txtPassword")).sendKeys("12345678910");
		driver.findElement(By.id("txtCPassword")).sendKeys("12345678910");
		driver.findElement(By.id("txtPhone")).sendKeys("09875566778");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Assert 
		//compare error message with expected result
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
	}
	
	@Test
	public void TC_04_Invalid_Password () {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Action
		//input data to web element - text boxes
		driver.findElement(By.id("txtFirstname")).sendKeys("hanhhong");
		driver.findElement(By.id("txtEmail")).sendKeys("hanh.nguyenthihong88@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hanh.nguyenthihong88@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234");
		driver.findElement(By.id("txtPhone")).sendKeys("09875566778");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Assert error message with expected result
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}
	@Test
	public void TC_05_Incorrect_Password () {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Action
		//input data to web element - text boxes
		driver.findElement(By.id("txtFirstname")).sendKeys("hanhhong");
		driver.findElement(By.id("txtEmail")).sendKeys("hanh.nguyenthihong88@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hanh.nguyenthihong88@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234567891");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567892");
		driver.findElement(By.id("txtPhone")).sendKeys("09875566778");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Assert error message with expected result
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
	}
	
	@Test
	public void TC_06_Incorrect_Phone () {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//Action 1 - input data to web element - text boxes
		driver.findElement(By.id("txtFirstname")).sendKeys("hanhhong");
		driver.findElement(By.id("txtEmail")).sendKeys("hanh.nguyenthihong88@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("hanh.nguyenthihong88@gmail.com");
		driver.findElement(By.id("txtPassword")).sendKeys("1234567891");
		driver.findElement(By.id("txtCPassword")).sendKeys("1234567891");
		driver.findElement(By.id("txtPhone")).sendKeys("09875");
		
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Assert 1 - Assert error message with expected result
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		//Action 2 - input data to web element - text boxes
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("098751234567");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Assert 2 - Assert error message with expected result
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		//Action 3 - input data to web element - text boxes
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("028751234567");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		//Assert 3 - Assert error message with expected result
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
