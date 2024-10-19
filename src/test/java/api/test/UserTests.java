package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.userEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests 
{
	Faker faker;
	User userPayload;
	//public Logger logger;
	@BeforeClass
	public void setupData()
	{
		faker=new Faker();
		userPayload=new User();
		
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logs
		//logger=LogManager.getLogger(this.getClass());
		
	}
	@Test(priority=1)
	public void testPostUser()
	{
		//logger.info("**********Creating User***************");
		Response response=userEndPoints.createUser(userPayload);
		response.then().log().all();
		response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 200);
		//logger.info("**********User is Created***************");
	}
	
	@Test(priority=2)
	public void testGetUserByName()
	{
		//logger.info("**********Reading User info***************");
		Response response=userEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().body();
		response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 200);
		//logger.info("**********User info is displayed ***************");
	}
	
	
	@Test(priority=3)
	public void testUpdateUserByName()
	{
		//logger.info("**********Updateing User***************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		
		Response response=userEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		//Another assertion
		response.then().log().body().statusCode(200); //CHAI assertion
		System.out.println("Body Before Update");
		System.out.println(response.then().log().body());
		response.statusCode();
		//TestNG assertion
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//checking data after update
		Response responseAfterUpdate=userEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);	
		System.out.println("Body After Update");
		System.out.println(responseAfterUpdate.then().log().body());
		//logger.info("********** User updates***************");
	}
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		//logger.info("**********Deleting User***************");
		Response response=userEndPoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 200);
		//logger.info("**********User deleted***************");
	}

}
