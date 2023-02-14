package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpartanTests {

    String spartanBaseUrl = "http://34.205.141.218:8000";

    @Test
    public void viewSpartanTest1(){

        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");

        System.out.println("response.statusCode() = " + response.statusCode());

        System.out.println("response.body().prettyPrint() = " + response.body().prettyPrint());

    }
    //When user send get request to /api/spartans endpoint
    // Then status code must be 200
    // And response body should contains "Allen"
    @Test
    public void viewSpartanTest2(){
        Response response = RestAssured.get(spartanBaseUrl + "/api/spartans");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("Allen"));

    }
    /*
   Given Accept type is json
   When user sends a get request to spartanAllUrl
   Then response status code is 200
   And response body should be in Json format
        */
    @Test
    public void viewSpartanTest3(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseUrl + "/api/spartans");
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertTrue(response.body().asString().contains("Mike"));

        response.prettyPrint();

    }



}
