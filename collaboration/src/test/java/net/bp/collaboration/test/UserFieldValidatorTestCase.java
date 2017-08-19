package net.bp.collaboration.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import net.bp.collaboration.validator.UserFieldValidator;

public class UserFieldValidatorTestCase {

	private static UserFieldValidator userFieldValidator = null;
	
	@BeforeClass
	public static void init() {
		userFieldValidator =  new UserFieldValidator();
	}
	

	@Test 
	public void testValidateUsername() {
		// valid username fields with capital, small and underscore
		String [] usernames = 
			new String [] {"Bhavna4987", "bhavna4987", "bhav2017", "bhavna_pal4987", "B49872017Null"};
		
		for(String username : usernames) {
			assertTrue("Failed to validate!", userFieldValidator.validateUsername(username));
		}

		
		usernames = 
			new String [] {"1Bhavna4987", "bhavna$4987", "bhavna*i2017", "$12pal4987", "B49872017$Null"};
		
		for(String username : usernames) {
			assertFalse(username, userFieldValidator.validateUsername(username));
		}
		
		
	}

	@Test 
	public void testValidatePassword() {
		// valid password field with capital, small, digit and special letter
		String [] passwords = 
			new String [] {"Bhavna&4987", "$Bhavna4987", "Bhavna^2017", "B_pal4987", "B49872017$Null"};
		
		for(String password : passwords) {
			assertTrue(password, userFieldValidator.validatePassword(password));
		}

		// invalid password field missing any capital, small, digit and special letter		
		passwords = 
				new String [] {"bhavna&4987", "$bhavna4987", "Bhavna2017", "_havna4987", "B49872017Null"};	
		
		for(String password : passwords) {
			assertFalse(password, userFieldValidator.validatePassword(password));
		}
		
		
	}
	
	
	
}
