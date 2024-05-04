package com.rocketninja.automation.search_mouse.ui.common;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.rocketninja.automation.search_mouse.ui.config.TestConfig;

public abstract class UIBaseTestCase {

	protected WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	public void setUp(){
		
		TestConfig.loadTestConfigProps();
		String chromeHomePropertyValue = TestConfig.props.getProperty("chrome.driver.home");
		String path = (chromeHomePropertyValue.equals("driver")) ? System.getProperty("user.dir") + File.separator + TestConfig.props.getProperty("chrome.driver.home") 
						+ File.separator + "chromedriver.exe" : chromeHomePropertyValue;
		
		System.setProperty("webdriver.chrome.driver", path);
		
		boolean headless = Boolean.valueOf(TestConfig.props.getProperty("chrome.headless"));
		ChromeOptions options = new ChromeOptions();
		options.setHeadless(headless);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown(){
		
		Reporter.log("Closing driver...", true);
		driver.close();
		driver.quit();
		
	}
	
}
