package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Custom_Dropdown {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
//		1 - Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector("span#speed-button")).click();
//		2 - Chờ cho tất cả các item được load ra thành công
//		locator phai lay sao cho dai dien duoc het cac item
//		lay den the chua text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#speed-menu div[role = 'option']")));
//		dua het cac items trong dropdown vao 1 cai list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector("ul#speed-menu div[role = 'option']"));
//		3 - Tìm item xem đúng cái đang cần hay ko dung vong lap
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
//		4 - Kiểm tra cái text của item đúng vs cái mình mong muốn			
			if(itemText.equals("Fast") ){
//		5 - Click vào item đó
				tempItem.click();
//			thoat ra khoi vong lap va khong xet cac case con lai
				break;
			}
		}
	}

	@Test
	public void TC_02_() {

	}

	@Test
	public void TC_03_() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
