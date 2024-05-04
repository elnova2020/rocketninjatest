package com.rocketninja.automation.search_mouse.ui.tests;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
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

public class InvalidSearchParametersValuesTests extends UIBaseTestCase {

	@DataProvider( name = "SearchParametersInvalidValues" )
	public Object[][] searchParametersWithInvalidValues() {
			//negative scenarios related to not acceptable input values
			return new Object[][]  {
				{
					new SearchMouseTestParams
					( 
						"TC-INV-1", "Unacceptable values for hand length, hand width fields - hand length field - only spaces.", 
						MeasureUnits.Inches, new HandSize("      ", "3.9"), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Width and Length can only contain digits"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-2", "Unacceptable values for hand length, hand width fields. Hand length negative value.", 
						MeasureUnits.Inches, new HandSize("-9.3", "3.9"), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Width and Length can only contain digits"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-3", "Unacceptable values for hand length, hand width fields. Hand length, width fields are empty.", 
						MeasureUnits.Inches, new HandSize("", ""), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Please enter values for length and width"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-4", "Unacceptable values for hand length, hand width fields. Hand length field's empty.", 
						MeasureUnits.Inches, new HandSize("", "3.5"), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Please enter values for length and width"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-5", "Unacceptable values for hand length, hand width fields. Hand length, width fields are empty.", 
						MeasureUnits.Inches, new HandSize("99999999999999999999999999999", "123456789"), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Any"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-6", "Unacceptable values for hand length, hand width fields. Hand length, width fields contain not digital content.", 
						MeasureUnits.Inches, new HandSize("dfg23.8DDDDdfgdgdgdg", "fddf3.5fsfsfs"), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Width and Length can only contain digits"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-7", "Unacceptable values for hand length, hand width fields. Hand length, width fields contain not digital content.", 
						MeasureUnits.Inches, new HandSize("&%$#@!23.8DD\"\"", "66"), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Width and Length can only contain digits"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-8", "Unacceptable values for hand length, hand width fields. Hand length, width fields contain zeros.", 
						MeasureUnits.Inches, new HandSize("0", "0"), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Any"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-8", "Unacceptable values for hand length, hand width fields. Hand length, width fields contain zeros.", 
						MeasureUnits.Inches, new HandSize("-0", "0"), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Width and Length can only contain digits"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-9", "Hand length is more than max length (9.8 inch) existing in DB. Expected: alert with explanation message.", 
						MeasureUnits.Inches, new HandSize(9.8f, 3.95f), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Unfortunately there are no mice in the database that fit your measurements!\n"
					+ "More mice are being made so try again later. For now you can increase the Leniency setting to expand your search range"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-10", "Hand length is more than max length (9.8 inch) existing in DB. Expected: alert with explanation message.", 
						MeasureUnits.Inches, new HandSize("9.0", "3.0"), GripType.Claw, Leniency.Medium, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Unfortunately there are no mice in the database that fit your measurements!\n"
					+ "More mice are being made so try again later. For now you can increase the Leniency setting to expand your search range"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-11", "Hand length is more than max length (24.3 cm) existing in DB. Expected: alert with explanation message.", 
						MeasureUnits.CM, new HandSize(25f, 5f), GripType.Claw, Leniency.VeryHigh, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(3) 
					),
					"Unfortunately there are no mice in the database that fit your measurements!\n"
					+ "More mice are being made so try again later. For now you can increase the Leniency setting to expand your search range"
				},
				{
					new SearchMouseTestParams
					( 
						"TC-INV-12", "Large number of buttons - more than max acceptable - 25. Expected: alert with explanation message.", 
						MeasureUnits.Inches, new HandSize(9.8f, 3.95f), GripType.Claw, Leniency.VeryHigh, Shape.Both, 
						Wireless.Unchecked, Lefthanded.Checked, new ButtonsNumber(300) 
					),
					"Unfortunately there are no mice in the database that fit your measurements!\n"
					+ "More mice are being made so try again later. For now you can increase the Leniency setting to expand your search range"
				}
			};
	}
	/***
	 * This data driven test case runs with data provider that contains invalid input data cases.
	 * Check that relevant alerts appears.
	 * @param searchParams
	 * @param expAlertMessage
	 * @throws Exception
	 */
	@Test( groups = { "search-mice-invalid-values"}, dataProvider = "SearchParametersInvalidValues", enabled = true )
	public void testInvalidInputValuesInSearchForm( SearchMouseTestParams searchParams, String expAlertMessage ) throws Exception {
		
		SearchMousePage searchMousePage = new SearchMousePage(driver);
		
		searchMousePage.goToPage();

		searchMousePage.fillSearchParametersForm(searchParams.getSearchParamsMap());

		searchMousePage.clickOnSearchButton();

		Thread.sleep(300);
		
		Reporter.log(String.format("Expected alert with message '%s'", expAlertMessage), true);
		Alert alert = driver.switchTo().alert();
		
		String alertMessage = driver.switchTo().alert().getText();
		
		Reporter.log("An alert has been got with the message : " + alertMessage, true);
		alert.accept();
		
		if ( !expAlertMessage.equals("Any") ){
			Assert.assertEquals(alertMessage, expAlertMessage);
		}
			
	}
	
}
