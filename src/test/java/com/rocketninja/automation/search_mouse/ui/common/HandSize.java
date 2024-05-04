package com.rocketninja.automation.search_mouse.ui.common;

public class HandSize {
	
	private String handLength;
	private String handWidth;

	public HandSize( float handLength, float handWidth ){
		this.handLength = String.valueOf(handLength);
		this.handWidth = String.valueOf(handWidth);
	}

	public HandSize( String handLength, String handWidth ){
		this.handLength = handLength;
		this.handWidth = handWidth;
	}

	public String getLengthS(){
		return handLength;
	}

	public String getWidthS(){
		return handWidth;
	}

	public float getLengthF(){
		return Float.valueOf(handLength);
	}

	public float getWidthF(){
		return Float.valueOf(handWidth);
	}


}
