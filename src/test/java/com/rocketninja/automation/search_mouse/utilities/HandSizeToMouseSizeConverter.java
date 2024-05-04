package com.rocketninja.automation.search_mouse.utilities;

public class HandSizeToMouseSizeConverter {

	//it is a mock function - it is not known the formula
	public static MouseSizeSearchParams calculateMouseSizeSearchParams( 
			float handLength, 
			float handWidth, 
			int leniency,
			String units){
		
		return new MouseSizeSearchParams( 10.0f, 13.5f, 4.5f, 6.7f );
	}
	
}
