package ApiTests;

import static io.restassured.RestAssured.*;

import com.beust.ah.A;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestsWithQueryParameters {


    @BeforeClass
    public void baseURI() {
        baseURI = "http://34.205.141.218:8000";
    }

    @Test
    public void QueryParam1() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")
                .and().queryParam("nameContains", "J")
                .when().get("/api/spartans/search");

 assertTrue(response.body().asString().contains("Female"));
 assertFalse(response.body().asString().contains("Male"));
 assertTrue(response.body().asString().contains("Janette"));
 assertEquals(response.statusCode(),200);
 assertEquals(response.contentType(),"application/json");
    response.prettyPrint();

    }
    @Test
    public void queryPAram2(){
        Map<String,Object> paramsMap = new HashMap<>();
        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","J");

        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)
                .when().get("api/spartans/search");

        assertTrue(response.body().asString().contains("Female"));
        assertFalse(response.body().asString().contains("Male"));
        assertTrue(response.body().asString().contains("Janette"));
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");
        response.prettyPrint();
    }
}