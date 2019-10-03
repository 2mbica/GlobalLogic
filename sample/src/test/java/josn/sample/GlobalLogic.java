package josn.sample;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

/**
 * @author aanthannagar
 *
 */
/**
 	   Verify that Status Code is 200
     - Verify the Respose Body: Explicitly verify that the data contains 6 data objects
 *
 */
public class GlobalLogic<Oject> {

	final static String ROOT_URI = "https://reqres.in/api";
	
	/**
	Verify that Status Code is 200
  - Verify the Respose Body
  	Explicitly verify that the data contains 6 data objects
	 *
	 */
	@Test
	public void get_users() {
		System.out.println("************Test Case 1****************");
		Response response = given()
				.when()
				.get(ROOT_URI + "/users")
				.then()
				.statusCode(200)
				.extract()
				.response();
		try
		{

			RestAssured.defaultParser = Parser.JSON;
			int statusCode = response.getStatusCode();
			Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
			List<Map<String, String>> users = response.jsonPath().getList("data");
			int numberOfUsers =users.size();
			Assert.assertEquals(numberOfUsers /*actual value*/, 6 /*expected value*/, "Verified 6 data elements ");
			Object value = null;
			for(int i=0;i<users.size();i++)
			{
				for (Entry<String, String> keyname : users.get(0).entrySet())
				{

					System.out.println(keyname);

				}
				System.out.println("*****************************");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	   Verify that Status Code is 200 for the first user
     - Verify the Respose Body
     - Verify the JSON Schema
	 *
	 */
	@Test
	public void get_user_one() {
		System.out.println("************Test Case 2****************");
	    Response response = given()
	            .when()
	            .get(ROOT_URI + "/users/1")
	            .then()
	            .statusCode(200)
	            .extract()
	            .response();
	    try
	    {
	    	
	        RestAssured.defaultParser = Parser.JSON;
	        int statusCode = response.getStatusCode();
	        Assert.assertEquals(statusCode , 200 , "Correct status code returned");
	        Map<String, String> users = response.jsonPath().getMap("data");
	        int numberOfUsers =users.size();
	        Assert.assertEquals(numberOfUsers , 5 , "Verified 6 data elements ");
	        Oject value = null;
	        	for (String keyname : users.keySet())
	            {
	        			value = (Oject) users.get(keyname);
	                	System.out.println(keyname+":"+value);
	                 
	            }
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
    }
		
	}
	/**
	  Verify that Status Code is 201 for the post call
  	  Verify the Respose Body
  	  Verify the JSON Schema
	 *
	 */
	@Test
	public void post_user() {
		System.out.println("************Test Case 3****************");
		String payload = "{\n" +
		        "  \"name\": \"morpheus\",\n" +
		        "  \"job\": \"zionresident\"\n"+
		        "}";
	    try
	    {
	    	 	Response response =	 given()
	    	            .contentType(ContentType.JSON)
	    	            .body(payload)
	    	            .post(ROOT_URI+"/users")
	    	            .then()
	    	            .statusCode(201)
	    	            .extract()
	    	            .response();
		        
	        RestAssured.defaultParser = Parser.JSON;
	        int statusCode = response.getStatusCode();
	        Assert.assertEquals(statusCode /*actual value*/, 201 /*expected value*/, "Correct status code returned");
	        Map<String, String> users = response.jsonPath().get("$");
			for (String keyname : users.keySet())
			{
				Object value = users.get(keyname);
				System.out.println(keyname+":"+value);
			}
	       }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
		
	}
	/**
	  Verify that Status Code is 400 for the post call
	  Verify the Respose Body
	  Verify the JSON Schema
	 *
	 */
	@Test
	public void post_incorrectuser() {
		System.out.println("************Test Case 4****************");
		String payload = "{\n" +
		        "  \"email\": \"sydney@fife\"\n"+
		        "}";
	    try
	    {
	    	 	Response response =	 given()
	    	            .contentType(ContentType.JSON)
	    	            .body(payload)
	    	            .post(ROOT_URI+"/register")
	    	            .then()
	    	            .statusCode(400)
	    	            .extract()
	    	            .response();
		        
	        RestAssured.defaultParser = Parser.JSON;
	        int statusCode = response.getStatusCode();
	        Map<String, String> users = response.jsonPath().get("$");
			for (String keyname : users.keySet())
			{
				Object value = users.get(keyname);
				System.out.println(keyname+":"+value);
			}
	        Assert.assertEquals(statusCode /*actual value*/, 400 /*expected value*/, "Correct status code returned");
	       }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
		
	}
	/**
	  Verify that Status Code is 200 for the put call
	  Verify the Respose Body
	  Verify the JSON Schema
	 *
	 */
	@Test
	public void put_user()
	{
	System.out.println("************Test Case 5****************");
		String payload = "{\n" +
				"  \"email\": \"sydney@fife\"\n"+
				"}";
		try
		{
			Response response =	 given()
					.contentType(ContentType.JSON)
					.body(payload)
					.put(ROOT_URI+"/users/2")
					.then()
					.statusCode(200)
					.extract()
					.response();
			RestAssured.defaultParser = Parser.JSON;
			int statusCode = response.getStatusCode();
			String res =response.getBody().asString();
			Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/, "Correct status code returned");
			Map<String, String> users = response.jsonPath().get("$");
			for (String keyname : users.keySet())
			{
				Object value = users.get(keyname);
				System.out.println(keyname+":"+value);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
