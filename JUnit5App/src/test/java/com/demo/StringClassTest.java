package com.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("<= String Class =>")
public class StringClassTest {
	
	static StringClass stringClass;
	
	@BeforeAll
	public static void setUp() {
		stringClass=new StringClass();
	}
	
	@Test
	@DisplayName("Positive length Test")
	public void lengthOfStringPositiveTest() {
		String input="JUnit test case";
		assertEquals(15,stringClass.getLengthOfInputString(input));
	}
	
	@Test
	@DisplayName("Negative length Test")
	//@Tag("test")
	public void lengthOfStringNegativeTest() {
		String input="Java And JUnit";
		assertNotEquals(10, stringClass.getLengthOfInputString(input));
	}
	
	
	@Test
	@DisplayName("Positive trim test")
	public void trimingStringPositiveTest() {
		String input="  Bonjour  ";
		assertEquals("Bonjour", stringClass.getTrimString(input));
	}
	
	@Test
	@DisplayName("Negative trim test")
	public void trimingStringNegativeTest() {
		String input="  Bon jour  ";
		assertNotEquals("Bonjour", stringClass.getTrimString(input));
	}
	
	@Test
	@DisplayName("SubString Postive test")
	public void substringPositiveTest() {
		String input="aymen", partOfString="ay";
		assertEquals(true, stringClass.isContainsSubstring(input, partOfString));
	}
	
	@Test
	@DisplayName("SubString Negative test")
	public void substringNegativeTest() {
		String input="aymen", partOfString="az";
		assertNotEquals(true, stringClass.isContainsSubstring(input, partOfString));
	}
	
	@Test
	@DisplayName("StartWith Positive test")
	public void isStringStartsWithPositiveTest() {
		String input="aymen", startCharacter="a";
		assertEquals(true, stringClass.isContainsSubstring(input, startCharacter));
	}
	
	@Test
	@DisplayName("StartWith Negative test")
	public void isStringStartsWithNegativeTest() {
		String input="aymen", startCharacter="r";
		assertNotEquals(true, stringClass.isContainsSubstring(input, startCharacter));
	}
	

}
