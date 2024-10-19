package api.test;

import org.testng.Assert;

import org.testng.annotations.Test;

import api.endpoints.userEndPoints;
import api.payload.User;
import api.utilites.DataProviders;
import io.restassured.response.Response;

public class DDTests 
{
	@Test(priority=1,dataProvider="data",dataProviderClass = DataProviders.class)  //dataProviderClass = DataProviders.class --> it indicate where is it if dataprovider in same class so no need to write
	public void testPostUser(String userID, String userName, String fname, String lname,String useremail,String pwd, String ph)
	{
		User userPayload=new User();
		
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=userEndPoints.createUser(userPayload);
		response.then().log().all();
		response.statusCode();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass = DataProviders.class)  //dataProviderClass = DataProviders.class --> it indicate where is it if dataprovider in same class so no need to write
	public void testGetuserByName(String userName)
	{
		Response response=userEndPoints.readUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority=3,dataProvider="UserNames",dataProviderClass = DataProviders.class)  //dataProviderClass = DataProviders.class --> it indicate where is it if dataprovider in same class so no need to write
	public void testDeleteuserByName(String userName)
	{
		Response response=userEndPoints.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	

}
