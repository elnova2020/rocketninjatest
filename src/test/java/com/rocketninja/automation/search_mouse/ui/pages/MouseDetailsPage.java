package com.rocketninja.automation.search_mouse.ui.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

public class MouseDetailsPage {

	private WebDriver driver;
	
	public MouseDetailsPage(WebDriver drv){
		this.driver = drv;
	}
	
	public MouseDetailsPage(){}
	
	public void goToPage(String url){
		
		driver.manage().window().maximize();
		driver.get(url);
		
		String actualTitle = driver.getTitle();
		Reporter.log("Go To Page: page title is " + actualTitle, true);
	}
	
	public Map<String,String> fillMouseInfoFromMouseDetailsPage( Map<String,String> rowMap ){
		
		Map<String,String> mouseDetailsMap = new HashMap<>();
		
		WebElement mouseDetails = driver.findElement(By.className("product_attributes"));
		
		List<WebElement> rows = mouseDetails.findElements(By.xpath(".//tr"));
		
		for ( WebElement row : rows ) {
			
			String key = row.findElement(By.className("mouse_attribute_title")).getText();
			String value = row.findElement(By.className("mouse_attribute_value")).getText();
			
			mouseDetailsMap.put(key, value);
			
			switch (key){
				case "Shape":
					rowMap.put("shape", value);
					break;
				case "Cable":
					rowMap.put("wireless", value.equals("Wireless") ? "true" : "false");
					break;
				case "Buttons":
					rowMap.put("number_of_buttons", value);
					break;
				case "Side Buttons":
					rowMap.put("lefthanded", value.equals("Both") ? "true" : "false");
					break;
			}
			
		}
		
		return mouseDetailsMap;
	}
	
}
