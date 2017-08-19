package net.bp.collaboration.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.bp.collaboration.config.RootConfig;
import net.bp.collaboration.dto.User;
import net.bp.collaboration.model.DomainResponseModel;
import net.bp.collaboration.model.UserModel;
import net.bp.collaboration.service.UserService;

public class UserTestCase {

	
	private static AnnotationConfigApplicationContext context = null;
	private static UserService userService = null;
	public User user = null;
	DomainResponseModel model = null;
	
	/* Initialize the class */
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext(RootConfig.class);
		userService = (UserService) context.getBean("userService");
	}
	
	
	@Test
	public void testAdd() {
		
		user = new User();
		String random = UUID.randomUUID().toString().substring(26).toUpperCase();
		user.setFirstName("Bhavna");
		user.setLastName("Pal");
		user.setEmail("bhavna" + random +"@gmail.com");
		user.setPassword("Niit@2017");
		user.setUsername("bhavna" + random);
		user.setContactNumber("9819007634");
			
		// testing for user object
		model = userService.add(user);
		assertEquals("Failed to store user object!",201,model.getCode());
		
		// testing for null object
		model = userService.add(null);
		assertEquals("Testing with null object failed!", 499, model.getCode());		
	
	}
	
	@Test
	public void testAddDuplicate() {
		// checking for uniqueness of username and email
		user = new User();
		user.setFirstName("Bhavna");
		user.setLastName("Pal");
		user.setEmail("bhavna@gmail.com");
		user.setPassword("Niit@2017");
		user.setUsername("bhavnapal");
		user.setContactNumber("9819007634");
		// should not allow to store duplicate object
		model = userService.add(user);
		assertEquals ("Duplicate Object is getting Stored!",497, model.getCode());
	}
	
	

	@Test
	public void testGetByParam() {
		// checking for uniqueness of username and email
		user = userService.getByParam("username", "bhavnapal");
		
		// checking if with valid value is successful
		assertEquals("Not able to fetch the given user with parameter username!", "bhavnapal", user.getUsername());
		
		// checking if with invalid value is successful
		user = userService.getByParam("username", "niit2017");
		assertNull("Fetching a user by passing wrong value!", user);
		
		// checking if with invalid parameter is successful
		user = userService.getByParam("dummyparam", "niit2017");
		assertNull("Fetching a user by passing wrong parameter!", user);

		// checking if with null value is successful
		user = userService.getByParam(null, null);
		assertNull("Fetching a user by passing null value!", user);
		
	}
	
	
	

	@Test
	public void testValdiate() {
		
		user = new User();
		
		// with valid credentials
		user.setUsername("bhavnapal");
		user.setPassword("Niit@2017");
		model = userService.validate(user);
	
		assertEquals("Failed to fetch UserModel!", UserModel.class , model.getClass());

		// with invalid credentials
		user.setUsername("bhavna__pal");
		user.setPassword("Niit@2017");
		model = userService.validate(user);
		assertEquals("Failed to fetch DomainResponseModel!", DomainResponseModel.class , model.getClass());
		
		// with null values
		user.setUsername(null);
		user.setPassword(null);
		model = userService.validate(user);
		assertEquals("Failed to fetch DomainResponseModel!", DomainResponseModel.class , model.getClass());				
		
	}

	
	
}
