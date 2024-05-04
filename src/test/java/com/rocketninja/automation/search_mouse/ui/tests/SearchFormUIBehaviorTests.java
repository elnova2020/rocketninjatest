package com.rocketninja.automation.search_mouse.ui.tests;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.rocketninja.automation.search_mouse.ui.common.ButtonsNumber;
import com.rocketninja.automation.search_mouse.ui.common.HandSize;
import com.rocketninja.automation.search_mouse.ui.common.UIBaseTestCase;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.GripType;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.Lefthanded;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.Leniency;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.MeasureUnits;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.Shape;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.Wireless;
import com.rocketninja.automation.search_mouse.ui.pages.SearchMousePage;

public class SearchFormUIBehaviorTests extends UIBaseTestCase {

	SearchMousePage searchMousePage = null;
	
	/***
	 * This test case perfom search operation with valid data, go to cell 'Link'
	 * link -> mouse details page opened -> press on 'Back' browser button.
	 * Verify that search result table contains search results and not 'No results' text.
	 * Test fails, the bug was found.
	 * @throws Exception
	 */
	@Test( groups = { "search-mice-back"}, enabled = true )
	public void testGoFromMouseDetailsPageBackToSearchResult() throws Exception{
		
		SearchMouseTestParams searchParams = new SearchMouseTestParams
		( 
			"TC-BHV-1", "Search result table is not empty. Get mice list in results table. Click on the link of mouse details. On mouse details page click on browser back button."
					+ "Check that the results table contains a result of the search.", 
			MeasureUnits.Inches, new HandSize(9.3f, 3.95f), GripType.Claw, Leniency.Medium, Shape.Both, 
			Wireless.Checked, Lefthanded.Checked, new ButtonsNumber(3) 
		);
		
		searchMousePage = new SearchMousePage(driver);
		searchMousePage.goToPage();

		searchMousePage.fillSearchParametersForm(searchParams.getSearchParamsMap());

		searchMousePage.clickOnSearchButton();
		
		Thread.sleep(1000);
		
		List<Map<String,String>> resultsList = searchMousePage.retrieveMiceSearchResults();

		int rowsNumber = resultsList.size();
		Reporter.log("Search result has " + rowsNumber + " items in the table.", true);
		
		//go to mouse details page
		searchMousePage.openMouseDetailsLink();
		
		driver.navigate().back();
		driver.navigate().refresh(); //refresh to close ads modal
		
		//check that the result table still contains search results
		resultsList = searchMousePage.retrieveMiceSearchResults();
		
		int rowsNumberAfter = resultsList.size();
		Reporter.log("Search result has " + rowsNumberAfter + " items in the table.", true);
		if ( rowsNumberAfter == 1 ) {
			if ( resultsList.get(0).get("id").contains("No results") ) {
				Reporter.log("Results table appears with \"No results\" content.", true);
			}
		
				Assert.fail("Results table appears with \"No results\" content. Expected: full mice list from the search results.");
		}
	}
	
	/***
	 * This test case checks that search form can be submitted with ENTER key.
	 * Test fails, there is a bug.
	 * @throws Exception
	 */
	@Test( groups = { "search-mice-ui-behav"}, enabled = true )
	public void testEnterButtonOnSearchFormSubmit() throws Exception{
		
		SearchMouseTestParams searchParams = new SearchMouseTestParams
		( 
			"TC-BHV-2", "Search result table is not empty. Get mice list in results table. Click on the link of mouse details. On mouse details page click on browser back button."
					+ "Check that the results table contains a result of the search.", 
			MeasureUnits.Inches, new HandSize(9.3f, 3.95f), GripType.Claw, Leniency.Medium, Shape.Both, 
			Wireless.Checked, Lefthanded.Checked, new ButtonsNumber(3) 
		);
		
		searchMousePage = new SearchMousePage(driver);
		searchMousePage.goToPage();

		searchMousePage.fillSearchParametersForm(searchParams.getSearchParamsMap());

		Reporter.log("Press ENTER button after the search parameters have been filled. Expected: serach results table will bring imce details.", true);
		new Actions(driver).sendKeys(Keys.ENTER);
		
		Thread.sleep(1000);
		
		List<Map<String,String>> resultsList = searchMousePage.retrieveMiceSearchResults();
		
		int rowsNumber = resultsList.size();
		Reporter.log("Search result has " + rowsNumber + " items in the table.", true);
		
		if ( rowsNumber == 1 ) {
			if ( resultsList.get(0).get("id").contains("No results") ) {
				Reporter.log("Results table appears with \"No results\" content.", true);
			}
		}
		
		Reporter.log("Click on Search Button...");
		searchMousePage.clickOnSearchButton();
		
		Thread.sleep(1000);
		resultsList = searchMousePage.retrieveMiceSearchResults();
		
		int rowsNumberAfter = resultsList.size();
		Reporter.log("Search result has " + rowsNumberAfter + " items in the table.", true);
		
		if ( rowsNumber != rowsNumberAfter ){
			Assert.fail("When 'ENTER' key has been pressed the results table appears with \"No results\" content. Expected: full mice list from the search results like it happens after search button click.");
		}
	}
	
}
