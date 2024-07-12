package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UsersTest2 {
	
	Faker faker;
	User userpayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setUpData() {
	
	
	
	faker = new Faker();
	userpayload = new User();
	
	userpayload.setId(faker.idNumber().hashCode());
	userpayload.setUsername(faker.name().username());
	userpayload.setFirstName(faker.name().firstName());
	userpayload.setLastName(faker.name().lastName());
	userpayload.setEmail(faker.internet().safeEmailAddress());
	userpayload.setPassword(faker.internet().password(5, 10));
	userpayload.setPhone(faker.phoneNumber().cellPhone());
	
	logger = LogManager.getLogger(this.getClass());
	
}
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("------- Creating User --------");
		Response response = UserEndPoints2.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("------- User Created --------");
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("------- Reading User Info --------");
		Response response = UserEndPoints2.readUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("------- User Info is displayed --------");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		//update data using payload
		
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints2.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		//checking the data after update
		
		Response responseAfterUpdate = UserEndPoints2.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		Response response = UserEndPoints2.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	
}
