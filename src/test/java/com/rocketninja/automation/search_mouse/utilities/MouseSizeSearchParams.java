package com.rocketninja.automation.search_mouse.utilities;

public class MouseSizeSearchParams {

	private float  minLength = 0;
	private float maxLength = 0;
	private float minWidth = 0;
	private float maxWidth = 0;
	
	public float getMinLength() {
		return minLength;
	}

	public float getMaxLength() {
		return maxLength;
	}
	
	public float getMinWidth() {
		return minWidth;
	}

	public float getMaxWidth() {
		return maxWidth;
	}

	public MouseSizeSearchParams( float lMin, float lMax, float wMin, float wMax ){
		minLength = lMin;
		maxLength = lMax;
		minWidth = wMin;
		maxWidth = wMax;
	}
	
}
