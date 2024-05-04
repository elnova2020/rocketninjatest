package com.rocketninja.automation.search_mouse.ui.tests;

import java.util.HashMap;
import java.util.Map;

import com.rocketninja.automation.search_mouse.ui.common.*;
import com.rocketninja.automation.search_mouse.ui.common.SearchParamsDefinitions.*;

public class SearchMouseTestParams {

	private String testId;
	private String description;
	
	private Map<SearchParam,String> searchParams = new HashMap<SearchParam, String>();
	
	public enum SearchParam {
		
		MEAS_UNITS("units"),
		HAND_LENGTH("hlength"), 
		HAND_WIDTH("hwidth"),
		LENIENCY("leniency"),
		GRIP_TYPE("griptype"),
		SHAPE("shape"),
		WIRELESS("wireless"),
		LEFTHANDED("lefthanded"),
		NUM_OF_BTNS("number_of_buttons");
		
		private final String name;
		
		private SearchParam(String name){
			this.name = name;
		}
		
		public String getName(){
			return name;
		}
		
		@Override
		public String toString() {
			
			return name;
		}
	}
	
	public SearchMouseTestParams( String testId, String description, Map<SearchParam, String> paramsMap ){
		this.testId = testId;
		this.description = description;
		this.searchParams = paramsMap;
	}
	
	public  SearchMouseTestParams( String testId, String description, MeasureUnits measUnit, HandSize handSize, 
									GripType gripType, Leniency leniencyLevel, 
									Shape shape, Wireless isWireless, Lefthanded isLefthanded, ButtonsNumber btnsNum ) {
		this.testId = testId;
		this.description = description;
		searchParams.put(SearchParam.MEAS_UNITS, measUnit.toString());
		searchParams.put(SearchParam.HAND_LENGTH, handSize.getLengthS());
		searchParams.put(SearchParam.HAND_WIDTH, handSize.getWidthS());
		searchParams.put(SearchParam.LENIENCY, leniencyLevel.toString());
		searchParams.put(SearchParam.GRIP_TYPE, gripType.getGripType());
		searchParams.put(SearchParam.SHAPE, shape.getShape());
		searchParams.put(SearchParam.WIRELESS, isWireless.toString());
		searchParams.put(SearchParam.LEFTHANDED, isLefthanded.toString());
		searchParams.put(SearchParam.NUM_OF_BTNS, btnsNum.toString());
		
	}
	
	public Map<SearchParam, String> getSearchParamsMap(){
		return searchParams;
	}
	
	@Override
	public String toString() {
		
		return "Test id: " + testId + ", description : " + description + "\nparameters :" +  searchParams.toString();
	}
	
//	public static Map<String, String> fromJson(String json) throws Exception {
////		String json = "{ 	\"units\" : \"Inches\", \"hlength\" : \"9.25\", \"hwidth\" : \"3.85\","
////				+ " \"leniency\" : \"2\", \"griptype\" : \"CLAW\", \"shape\" : \"both\", \"wireless\" : \"fasle\","
////				+ " \"lefthanded\" : \"false\", \"number_of_buttons\" : \"3\" }");
//
//        ObjectMapper mapper = new ObjectMapper();
//        HashMap<String, String> map = mapper.readValue(json, HashMap.class);
//        
//        return map;
//	}
	
}
