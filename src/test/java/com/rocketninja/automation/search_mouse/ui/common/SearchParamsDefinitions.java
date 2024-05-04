package com.rocketninja.automation.search_mouse.ui.common;

public class SearchParamsDefinitions {
	public enum MeasureUnits {
		Inches("Inches"),
		CM("CM");
		
		private final String meas;
		
		private MeasureUnits(String value){
			this.meas = value;
		}
		
		public String getMeasUnits(){
			return meas;
		}
		
		@Override
		public String toString() {
			return meas;
		}
	}
	
	public enum GripType {
		FingerTip("FINGERTIP"),
		Claw("CLAW"),
		ClawPalm("CLAW-PALM"),
		Palm("PALM");
		
		private final String griptype;
		
		private GripType(String value){
			this.griptype = value;
		}
		
		public String getGripType(){
			return griptype;
		}
	}
	
	public enum Shape {
		Both("both"),
		Symmetrical("symmetrical"),
		Asymmetrical("asymmetrical");
		
		private final String shape;
		
		private Shape(String value){
			this.shape = value;
		}
		
		public String getShape(){
			return shape;
		}
	}
	
	public enum Leniency {
		VeryLow(0),
		Low(1),
		Medium(2),
		High(3),
		VeryHigh(4);
		
		private final int value;
		
		private Leniency(int value){
			this.value = value;
		}
		
		public int getValue(){
			return value;
		}
		
		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}
	
	public enum Wireless {
		Checked(true),
		Unchecked(false);
		
		private final boolean value;
		
		private Wireless(boolean value){
			this.value = value;
		}
		
		public boolean getValue(){
			return value;
		}
		
		@Override
		public String toString() {
			
			return String.valueOf(value);
		}
	}
	
	public enum Lefthanded {
		Checked(true),
		Unchecked(false);
		
		private final boolean value;
		
		private Lefthanded(boolean value){
			this.value = value;
		}
		
		public boolean getValue(){
			return value;
		}
		
		@Override
		public String toString() {
			
			return String.valueOf(value);
		}
	}
	
}
