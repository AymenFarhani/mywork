package com.demo;

public class StringClass {
	
	public int getLengthOfInputString(String input) {
		return input.length();
	}
	
	public String getTrimString(String input) {
		return input.trim();
	}
	
	public boolean isContainsSubstring(String input, String partOfString) {
		return input.contains(partOfString);
	}
	
	public boolean isStringStartsWith(String input, String startsWithCharacter) {
		return input.startsWith(startsWithCharacter);
	}
	

}
