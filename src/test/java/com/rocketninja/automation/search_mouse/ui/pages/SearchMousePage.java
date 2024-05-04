package com.rocketninja.automation.search_mouse.ui.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import com.rocketninja.automation.search_mouse.ui.tests.SearchMouseTestParams.SearchParam;

public class SearchMousePage {
	
		private final String url = "https://www.rocketjumpninja.com/mouse-search";
		private WebDriver driver;
		
		private final String selectUnitsBoxId = "measurement";
		private final String searchButtonId = "searchButton";
		private final String resultsTableId = "resultsTable";
		private final String elInputLocator = "//fieldset[legend[text()='%s']]//input[@name='%s']";
		private final String elRadioBtnLocator = "//fieldset[legend[text()='%s']]//input[@name='%s' and @value='%s']";
		
		private final String expectedPageTitle = "Mouse Search | Rocket Jump Ninja";
		
		public SearchMousePage(WebDriver drv){
			this.driver = drv;
		}
		
		public SearchMousePage(){}
		
		public void goToPage(){
			
			Reporter.log("SearchMousePage: go to page " + url, true);
			driver.manage().window().maximize();
			driver.get(url);
			
			String actualTitle = driver.getTitle();
			Reporter.log("Go To Page: got page with title " + actualTitle, true);
			Assert.assertEquals(actualTitle, expectedPageTitle);
		}
		
		public void fillSearchParametersForm( Map<SearchParam, String> searchParamsMap ){
			
			//enter test data
			selectMeasurementUnit(searchParamsMap.get(SearchParam.MEAS_UNITS));

			//hand size
			setHandLength(searchParamsMap.get(SearchParam.HAND_LENGTH));
			setHandWidth(searchParamsMap.get(SearchParam.HAND_WIDTH));
			
			setGripType(searchParamsMap.get(SearchParam.GRIP_TYPE));
			setLeniency(searchParamsMap.get(SearchParam.LENIENCY));
			setShape( searchParamsMap.get(SearchParam.SHAPE) );
			
			//options
			setWireless( searchParamsMap.get(SearchParam.WIRELESS));
			setLefthanded(searchParamsMap.get(SearchParam.LEFTHANDED));
			setNumberOfButtons(searchParamsMap.get(SearchParam.NUM_OF_BTNS));
			
		}
		
		public void setHandLength(String value){
			putTextValue("Hand Size", "h_length", value );
		}
		
		public void setHandWidth(String value){
			putTextValue("Hand Size", "h_width", value );
		}
		
		public void setGripType( String gripType ) {
			selectRadioButton("Grip Type", "grip_type", gripType);
		}
		
		public void setLeniency( String leniencyLevel ) {
			selectRadioButton("Search Leniency ", "leniency", leniencyLevel);
		}
		
		public void setShape( String shape ){
			selectRadioButton("Shape", "shape", shape);
		}
		
		public void setWireless( String onOff ){
			selectCheckBox("Options", "wireless", onOff);
		}
		
		public void setLefthanded( String onOff ){
			selectCheckBox("Options", "lefthanded", onOff);
		}
		
		private void selectRadioButton( String sectionName, String btnName, String value ){
			
			System.out.println( "Locator is " + String.format(elRadioBtnLocator, sectionName, btnName, value ) );
			
			WebElement btn = driver.findElement(By.xpath(String.format(elRadioBtnLocator, sectionName, btnName, value )));
			btn.click();
		}
		
		private void putTextValue( String sectionName, String inputName, String value ){
			WebElement textField = driver.findElement(By.xpath(String.format(elInputLocator, sectionName, inputName )));
			
			textField.sendKeys(value);
		}
		
		private void selectCheckBox( String sectionName, String chkBoxName, String value ){
			WebElement chkBox = driver.findElement(By.xpath(String.format(elInputLocator, sectionName, chkBoxName )));
			
			if ( Boolean.valueOf(value) && !chkBox.isSelected()){
				chkBox.click();
			}
		}
		
		public void setNumberOfButtons( String number ){
			
			final String section = "Options";
			final String elementName = "buttons";
			
			WebElement inputBox = driver.findElement(By.xpath(String.format(elInputLocator, section, elementName )));
			inputBox.sendKeys(String.valueOf(number));
		}
				
		public void selectMeasurementUnit( String value ){
			Select dropdown = new Select(driver.findElement(By.id(selectUnitsBoxId)));
			dropdown.selectByVisibleText(value);
		}
		
		public void clickOnSearchButton(){
			WebElement searchButton = driver.findElement(By.id(searchButtonId));
			searchButton.click();
		}
		
		public List<Map<String,String>> retrieveMiceSearchResults() {
			
			List<Map<String,String>> results = new ArrayList<Map<String,String>>();
			WebElement resultTable = driver.findElement(By.id(resultsTableId));
			
			List<WebElement> rows = resultTable.findElements(By.xpath(".//tr"));
			
			for ( WebElement row : rows ) {
				
				Map<String,String> rowMap = new HashMap<String,String>();
				List<WebElement> cells = row.findElements(By.xpath(".//td"));
				
				int cellIdx = 0;
				for (WebElement cell : cells) {
					switch (cellIdx) {
						case 0:
							rowMap.put("id", cell.getText() );
							break;
						case 1:
							rowMap.put("name", cell.getText() );
							break;
						case 4:
							rowMap.put("hlength", cell.getText() );
							break;
						case 5:
							rowMap.put("hwidth", cell.getText() );
							break;
						case 6:
							WebElement link = cell.findElement(By.xpath(".//a"));
							rowMap.put("link", link.getAttribute("href") );
							break;
					}
					
					cellIdx++;
				}
				
				results.add(rowMap);
			}
			
			return results;
		}
				
		public void openMouseDetailsLink( ){
			
			WebElement resultTable = driver.findElement(By.id(resultsTableId));
			
			List<WebElement> rows = resultTable.findElements(By.xpath(".//tr"));
			List<WebElement> cells = rows.get(0).findElements(By.xpath(".//td"));
			WebElement link = cells.get(6).findElement(By.xpath(".//a"));
			
			
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView();", link);
			js.executeScript("arguments[0].click();", link);
			
		}
		
}
