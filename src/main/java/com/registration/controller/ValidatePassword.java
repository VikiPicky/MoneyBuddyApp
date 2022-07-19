package com.registration.controller;

public class ValidatePassword {

	final int NUM_UPPER_LETTERS = 1;
	final int NUM_LOWER_LETTERS = 1;
	final int NUM_DIGITS = 1;
	int upperCount = 0;
	int lowerCount = 0;
	int digitCount = 0;
	int letterCount = 0;

	public String getErrorMessage(String password) {
		
		String message = null;
		int inputLength = password.length();

		for (int i = 0; i < inputLength; i++) {
			char ch = password.charAt(i);
			if (Character.isUpperCase(ch))
				upperCount++;
			else if (Character.isLowerCase(ch))
				lowerCount++;
			else if (Character.isDigit(ch))
				digitCount++;
		}
		if (upperCount >= NUM_UPPER_LETTERS && lowerCount >= NUM_LOWER_LETTERS && digitCount >= NUM_DIGITS) {
			return null;
		}

		else {
			if (upperCount < NUM_UPPER_LETTERS) {
				message = "IMPORTANT: Invalid Password not enough: upper case letters";
			}

			if (lowerCount < NUM_LOWER_LETTERS) {
				message = "IMPORTANT: Invalid Password not enough: lower case letters";
			}

			if (digitCount < NUM_DIGITS) {
				message = " IMPORTANT: Invalid Password not enough: digits";
			}
		} System.out.print(message);
		return message;

	}
}
