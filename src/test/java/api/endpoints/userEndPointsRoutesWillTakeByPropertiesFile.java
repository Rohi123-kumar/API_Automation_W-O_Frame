package api.endpoints;
//userEndPoint.Java
//Created for Perform Create, Read, Update, Delete requests to the user API.
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class userEndPointsRoutesWillTakeByPropertiesFile 
{
	//method created for getting URL's from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routesfile=ResourceBundle.getBundle("routes"); //load properties file
		return routesfile;
	}
	public static  Response createUser(User payload)
	{
		String post=getURL().getString("post_url");
		
	    Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		.when()
		    .post(post);
	    return response;
		
	}
	
	public static  Response readUser(String userName)
	{
		String get=getURL().getString("get_url");
	Response response = given()
		    .pathParam("username", userName)
		.when()
		    .get(get);
	    return response;
		
	}
	
	public static  Response updateUser(String userName, User payload)
	{
		String update=getURL().getString("update_url");
	Response response = given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .pathParam("username", userName)
		    .body(payload)
		.when()
		    .put(update);
	    return response;
		
	}
	
	
	public static  Response deleteUser(String userName)
	{
		String delete=getURL().getString("delete_url");
	Response response = given()
		    .pathParam("username", userName)
		.when()
		    .delete(delete);
	    return response;
		
	}
	
}
