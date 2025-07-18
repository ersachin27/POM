package com.demo.seleniump.firstproject.locators;

public class LoginLocators {
	
	public static final String username = "exampleFormControlInput1";
	public static final String password = "floatingPassword";
	public static final String verifyText = "//h1[normalize-space()='Login Now']";
	public static final String logIn = "//button[normalize-space()='Submit']";
	
	
	//facebook
	public static final String usernames = "email";
	public static final String passwords = "pass";
	
	public static final String verifyTexts = "//h2[contains(text(),'Facebook helps you connect and share with the peop')]";
	public static final String logIns = "//button[@id='u_0_5_FV']";
}
