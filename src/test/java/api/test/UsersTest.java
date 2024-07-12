package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UsersTest {
	
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
		Response response = UserEndPoints.createUser(userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("------- User Created --------");
	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("------- Reading User Info --------");
		Response response = UserEndPoints.readUser(this.userpayload.getUsername());
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
		
		Response response = UserEndPoints.updateUser(this.userpayload.getUsername(), userpayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		//checking the data after update
		
		Response responseAfterUpdate = UserEndPoints.readUser(this.userpayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	}
	
	@Test(priority = 4)
	public void testDeleteUserByName() {
		Response response = UserEndPoints.deleteUser(this.userpayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	
}
