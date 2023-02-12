package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpartanTestsWithPathParameters {

    @BeforeClass
    public void baseURI() {
        RestAssured.baseURI = "http://34.205.141.218:8000";
    }
    @Test
    public void PathTest1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",20)
                .when().get("/api/spartans/{id}");

        Assert.assertEquals(response.contentType(),"application/json");
        Assert.assertEquals(response.statusCode(),200);

        Assert.assertTrue(response.body().asString().contains("Lothario"));
    }

    @Test
    public void negativePathTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 500)
                .when().get("/api/spartans/{id}");

        Assert.assertEquals(response.statusCode(),404);
        Assert.assertEquals(response.contentType(),"application/json");


    }


}
