package com.rocketninja.automation.search_mouse.ui.common;

//class wrapper for number of buttons search parameter, just for code readability 
public class ButtonsNumber {
	private int num;
	
	public ButtonsNumber(int btnsNum){
		num = btnsNum;
	}
	
	public int getNumber(){
		return num;
	}
	
	@Override
	public String toString() {
		
		return String.valueOf(num);
	}
}
