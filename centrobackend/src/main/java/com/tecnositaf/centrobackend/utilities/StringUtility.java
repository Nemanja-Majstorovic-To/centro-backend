package com.tecnositaf.centrobackend.utilities;

import java.util.Random;
import java.util.regex.Pattern;

public class StringUtility {

	public static boolean validateMail(String mailToValidate) {
		
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
							"[a-zA-Z0-9_+&*-]+)*@" + 
							"(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
							"A-Z]{2,7}$"; 
		           
		Pattern pat = Pattern.compile(emailRegex); 
		if (mailToValidate == null) 
			return false; 	
		return pat.matcher(mailToValidate).matches(); 
		
	}

	public static String generateCasualString(){
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		return generatedString;
	}
}