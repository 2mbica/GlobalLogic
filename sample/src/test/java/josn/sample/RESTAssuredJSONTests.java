package josn.sample;
import static io.restassured.RestAssured.given;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

public class RESTAssuredJSONTests {

final static String ROOT_URI = "https://jsonmock.hackerrank.com/api";
//https://www.testingexcellence.com/parse-json-response-rest-assured/

@Test
public void simple_get_test() {
	
    Response response = given()
            .queryParam("Title", "Waterworld")
            .when()
            .get(ROOT_URI + "/movies/search")
            .then()
            .statusCode(200)
            .extract()
            .response();
    System.out.println(response.asString());
    try
    {
    	
        RestAssured.defaultParser = Parser.JSON;
        String m = response.jsonPath().getString("page");
        System.out.println( m); 
        List<Map<String, String>> movies = response.jsonPath().getList("data");
        for(int i=0;i<movies.size();i++)
        {
        	System.out.println(movies.get(i).get("Title"));  
        }

    }
    catch (Exception e)
    {
    	e.printStackTrace();
    }
	
}
}