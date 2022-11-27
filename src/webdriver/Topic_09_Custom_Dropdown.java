package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

//	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
//		chon item trong speed dropdown list
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role = 'option']", "Fast");
		Sleepinsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button > span.ui-selectmenu-text")).getText(), "Fast");
		
		selectItemInDropdown("span#speed-button", "ul#speed-menu div[role = 'option']", "Slow");
		Sleepinsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button > span.ui-selectmenu-text")).getText(), "Slow");
		
		selectItemInDropdown("span#salutation-button", "ul#salutation-menu div[role = 'option']", "Prof.");
		Sleepinsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button > span.ui-selectmenu-text")).getText(), "Prof.");
	}

//	@Test
	public void TC_02_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInDropdown("i.dropdown.icon", "span.text", "Stevie Feliciano");
		Sleepinsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
		
		selectItemInDropdown("i.dropdown.icon", "span.text", "Jenny Hess");
		Sleepinsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
	}

//	@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		Sleepinsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		Sleepinsecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

	}
	
	public void Sleepinsecond(long Timeinseconds) {
		try {
			Thread.sleep(Timeinseconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// 1000ms = 1s
	
	public void selectItemInDropdown(String parentCss, String allCssItems, String expectedCssItem) {
//		1 - Click vào 1 thẻ bất kì để làm sao cho nó xổ ra hết các item của dropdown
		driver.findElement(By.cssSelector(parentCss)).click();
//		2 - Chờ cho tất cả các item được load ra thành công
//		locator phai lay sao cho dai dien duoc het cac item
//		lay den the chua text
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(allCssItems)));
//		dua het cac items trong dropdown vao 1 cai list
		List<WebElement> speedDropdownItems = driver.findElements(By.cssSelector(allCssItems));
//		3 - Tìm item xem đúng cái đang cần hay ko dung vong lap
		for (WebElement tempItem : speedDropdownItems) {
			String itemText = tempItem.getText();
			System.out.println(itemText);
//		4 - Kiểm tra cái text của item đúng vs cái mình mong muốn			
			if(itemText.trim().equals(expectedCssItem) ){
//		5 - Click vào item đó
				tempItem.click();
//			thoat ra khoi vong lap va khong xet cac case con lai
				break;
			}
		}
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
