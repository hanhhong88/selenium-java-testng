package webdriver;


import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
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
	public void TC_01 () {
		//>=2 tabs => close current tab
		//=1 tab => close tab
		driver.close(); //* - often used
		
		//don't care how many tabs are opened. Close browser.
		driver.quit(); //** - usually used
		
		//assign agurment to use in other cases
		WebElement arg1 = driver.findElement(By.xpath("//input[@id='email']")); //**
		arg1.clear();
		arg1.sendKeys("");
		
		//or using directly without assigning
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
	
		//Find multiple elements
		List<WebElement> arg2 = driver.findElements(By.xpath("")); //*
		
		//open 1 URL
		driver.get("https://www.facebook.com/");
		
		//get current URL, used to verify open URL correctly or not
		driver.getCurrentUrl();
		Assert.assertEquals(driver.getCurrentUrl(),"https://vi-vn.facebook.com/");
		
		
		//get HTML source code of the current URL to verify tuong doi 
		String arg3 = driver.getPageSource();
		Assert.assertTrue(driver.getPageSource().contains("Tin tức online, đọc báo Vietnamnet"));
		
		//get URL title to verify 
		driver.getTitle();
		Assert.assertEquals(driver.getTitle(), "[Online 27] - Topic 20 (WebBrowser Commands) - YouTube");
		
		//get ID of the current tab - active
		String arg4 = driver.getWindowHandle(); //*
		
		//get all different IDs of all opening tabs
		Set <String> arg5 = driver.getWindowHandles(); //*
		
		//Cookies/Cache
		Options arg6 = driver.manage();
		//get and save Cookies to reuse
		arg6.getCookies(); //*
		
		arg6.logs();
		
		//waiting time for an element appears
		Timeouts arg7 = arg6.timeouts();
		arg7.implicitlyWait(5,TimeUnit.SECONDS); //**
		arg7.implicitlyWait(5000,TimeUnit.MILLISECONDS);
		arg7.implicitlyWait(5000000,TimeUnit.MICROSECONDS);
		
		//loading time of a page
		arg7.pageLoadTimeout(5,TimeUnit.SECONDS);
		
		//set script timeout
		arg7.setScriptTimeout(5,TimeUnit.SECONDS);
		
		
		Window arg8 = arg6.window();
		arg8.fullscreen();
		arg8.maximize(); //**
		
		//test GUI: font/ size/colour/position
		arg8.getPosition();
		arg8.getSize();
		
		
		//
		Navigation arg9 = driver.navigate();
		arg9.back();
		arg9.refresh();
		arg9.forward();
		arg9.to(" "); //same as driver.get("");
		
		TargetLocator arg10 = driver.switchTo();
		arg10.alert(); //*
		arg10.frame(""); //*
		arg10.window("");//*
		
		
		
		
	}

	@Test
	public void TC_02 () {
		
	}

	@Test
	public void TC_03 () {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
