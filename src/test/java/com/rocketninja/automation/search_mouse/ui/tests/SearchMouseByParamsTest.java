package com.rocketninja.automation.search_mouse.ui.tests;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rocketninja.automation.search_mouse.ui.common.ButtonsNumber;
import com.rocketninja.automation.search_mouse.ui.common.HandSize;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.GripType;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.Lefthanded;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.Leniency;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.MeasureUnits;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.Shape;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.Wireless;
import com.rocketninja.automation.search_mouse.ui.common.UIBaseTestCase;
import com.rocketninja.automation.search_mouse.ui.pages.MouseDetailsPage;
import com.rocketninja.automation.search_mouse.ui.pages.SearchMousePage;
import com.rocketninja.automation.search_mouse.ui.tests.SearchMouseTestParams.SearchParam;
import com.rocketninja.automation.search_mouse.utilities.HandSizeToMouseSizeConverter;
import com.rocketninja.automation.search_mouse.utilities.MouseSizeSearchParams;

public class SearchMouseByParamsTest extends UIBaseTestCase {

	
	@DataProvider( name = "SearchParameters" )
		public Object[][] searchParameters() {
			//these 3 test cases are example of tests, should be added much more test cases for real testing
			return new Object[][] {
					
						{ new SearchMouseTestParams
							( 
								"TC-1", "Valid set of parameters - inches. Search Result table is expected to contain list of mice with the properties according to the search params.", 
								MeasureUnits.Inches, new HandSize(9.3f, 3.95f), GripType.Claw, Leniency.Medium, Shape.Both, 
								Wireless.Unchecked, Lefthanded.Unchecked, new ButtonsNumber(3) 
							)
						},
						{ new SearchMouseTestParams
							( 
								"TC-2", "Valid set of parameters - inches. Search Result table is expected to contain list of mice with the properties according to the search params.", 
								MeasureUnits.Inches, new HandSize(9.3f, 3.95f), GripType.ClawPalm, Leniency.High, Shape.Symmetrical, 
								Wireless.Checked, Lefthanded.Unchecked, new ButtonsNumber(5) 
							)
						},
						{ new SearchMouseTestParams
							( 
								"TC-3", "Valid set of parameters - cm. Search Result table is expected to contain list of mice with the properties according to the search params.", 
								MeasureUnits.CM, new HandSize(24.3f, 9.8f), GripType.Claw, Leniency.Medium, Shape.Both, 
								Wireless.Unchecked, Lefthanded.Unchecked, new ButtonsNumber(3) 
							)
						}
						
					};
			
		}
	
	/***
	 * This is data driven test case with data provider.
	 * Enter valid data into search mouse form – hand length, width should
	 * be in valid range, exist in all mouse data ( from all mice page ). Click
	 * on 'Search button. ' See that results table contains the relevant list of
	 * mice with mouse parameters that match input search parameters.
	 * @param searchParams
	 * @throws Exception
	 */
	@Test( groups = { "search-mice-match"}, dataProvider = "SearchParameters", enabled = true )
	public void testMouseDetailsMatchSearchParams( SearchMouseTestParams searchParams ) throws Exception {
			
		SearchMousePage searchMousePage = new SearchMousePage(driver);
		
		searchMousePage.goToPage();

		searchMousePage.fillSearchParametersForm(searchParams.getSearchParamsMap());

		searchMousePage.clickOnSearchButton();

		Thread.sleep(300);

		List<Map<String,String>> resultsList = searchMousePage.retrieveMiceSearchResults();

		//add mouse details from mouse details page - will be implemented as direct page opening from browser
		//but should be opened by click on Link text on results table cell - it will be possible when the issue of
		//advertising frame closing and go back to search results bug will be solved 
		for ( Map<String,String> map : resultsList ){
			MouseDetailsPage mouseDetailsPage = new MouseDetailsPage(driver);

			mouseDetailsPage.goToPage(map.get("link"));
			mouseDetailsPage.fillMouseInfoFromMouseDetailsPage(map);
		}

		Reporter.log("Verification....", true);
		verifyResultsMatchSearchParams(resultsList, searchParams.getSearchParamsMap());
			
	}
	
	//VERIFICATOR
	public void verifyResultsMatchSearchParams(List<Map<String,String>> resultsList, Map<SearchParam,String> searchParams) throws Exception {
		
		for ( Map<String,String> map : resultsList ){
			
			Reporter.log("Mouse " + map.get("name") + ": details match verification...", true);
			String problemsFoundMessage = "";
			boolean handSizeMatch = handSizeMatch( map, searchParams );
			if ( !handSizeMatch ){
				Reporter.log("Hand size doesn't match to search parameters. ");
				problemsFoundMessage += "Hand size doesn't match to search parameters.\n";
			}
			
			boolean shapeMatch = shapeMatch( map.get("shape"), searchParams.get(SearchParam.SHAPE) );
			if ( !shapeMatch ){
				Reporter.log("Shape doesn't match to search parameters. Expected: " + searchParams.get(SearchParam.SHAPE) + ", actual : " + map.get("shape"));
				problemsFoundMessage += "Shape -- expected: " + searchParams.get(SearchParam.SHAPE) + ", actual : " + map.get("shape") + "\n";
			}
			
			boolean wirelessMatch = wirelessMatch( map.get("wireless"), searchParams.get(SearchParam.WIRELESS) );
			if ( !wirelessMatch ){
				Reporter.log("Mice in result list doesn't match to wireless flag. Expected : " + searchParams.get(SearchParam.WIRELESS) + ", actual: " + map.get("wireless") );
				problemsFoundMessage += "Wireless:" + searchParams.get(SearchParam.WIRELESS) + ", actual: " + map.get("wireless") + "\n";
			}
			
			boolean lefthandedMatch = lefthandedMatch( map.get("lefthanded"), searchParams.get(SearchParam.LEFTHANDED) );
			if ( !lefthandedMatch ){
				Reporter.log("Mice in result list doesn't match to lefthanded flag. Expected : " + searchParams.get(SearchParam.LEFTHANDED) + ", actual: " + map.get("lefthanded") );
				problemsFoundMessage += "Lefthanded:" + searchParams.get(SearchParam.LEFTHANDED) + ", actual: " + map.get("lefthanded") + "\n";
			}
			
			boolean buttonsNumberMatch = buttonsNumberMatch( map.get("number_of_buttons"), searchParams.get(SearchParam.NUM_OF_BTNS) );
			if ( !buttonsNumberMatch ){
				Reporter.log("Number of buttons of the mouse doesn't match search params number. Expected : number of buttons more than or equal to " + searchParams.get(SearchParam.NUM_OF_BTNS) + ", actual: " +  map.get("number_of_buttons"));
				problemsFoundMessage += "Number of buttons: expected number of buttons more than or equal to " + searchParams.get(SearchParam.NUM_OF_BTNS) + ", actual: " +  map.get("number_of_buttons") + "\n";
			}
			
			Assert.assertEquals(handSizeMatch&&shapeMatch&&wirelessMatch&&lefthandedMatch&&buttonsNumberMatch, true, "Mouse " + map.get("id") + ", " + map.get("name") + ": details match verification failed.\n" + problemsFoundMessage);
			Reporter.log("Mouse " + map.get("name") + ": details match verification passed.", true);
		}
		
	}
	
	
	//HELPERS
	private boolean shapeMatch(String actual, String expected){
		
		if ( expected.equals("both") )
			return true;
		
		return expected.toLowerCase().equals(actual.toLowerCase());
	}
	
	private boolean wirelessMatch(String actual, String expected){
		if ( expected.equals("false") ) return true; //if wireless is unchecked so list can contain both: wireless and cable  
		return actual.equals(expected);
	}
	
	private boolean buttonsNumberMatch(String actual, String expected){
		return Integer.valueOf(actual) >= Integer.valueOf(expected);
	}
	
	private boolean lefthandedMatch(String actual, String expected){
		
		if ( expected.equals("false") ) return true; //if lefthanded is unchecked so list can contain both: left handed and right handed
		return expected.equals(actual);
	}
	
	private boolean handSizeMatch( Map<String, String> mouseDetails, Map<SearchParam, String> searchParams ) {
		
		MouseSizeSearchParams mouseSizeSearchParams = 
				HandSizeToMouseSizeConverter.calculateMouseSizeSearchParams(
				Float.valueOf(searchParams.get(SearchParam.HAND_LENGTH)),
				Float.valueOf(searchParams.get(SearchParam.HAND_WIDTH)),
				Integer.valueOf(searchParams.get(SearchParam.LENIENCY)),
				searchParams.get(SearchParam.MEAS_UNITS));
		
		boolean blength = false;
		boolean bwidth = false;
		
		if ( Float.valueOf(mouseDetails.get(SearchParam.HAND_LENGTH.getName())) >= mouseSizeSearchParams.getMinLength() && 
				Float.valueOf(mouseDetails.get(SearchParam.HAND_LENGTH.getName())) <= mouseSizeSearchParams.getMaxLength() ) {
			//length verification passed
			blength = true;
		}
		
		if ( Float.valueOf(mouseDetails.get(SearchParam.HAND_WIDTH.getName())) >= mouseSizeSearchParams.getMinWidth() && 
				Float.valueOf(mouseDetails.get(SearchParam.HAND_WIDTH.getName())) <= mouseSizeSearchParams.getMaxWidth() ) {
			//width verification passed
			bwidth = true;
		}
		
		return blength && bwidth;
	}
	
	
	
	

}
