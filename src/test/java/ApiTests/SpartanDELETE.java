package ApiTests;


import com.google.gson.Gson;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class SpartanDELETE {

    @BeforeClass
    public void baseURI() {
        RestAssured.baseURI = "http://34.205.141.218:8000";
    }

    @Test

    public void Test1(){

        given().pathParam("id",111)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

        //verify part
        given().pathParam("id",111)
                .when().delete("/api/spartans/{id}")
                .then().assertThat().statusCode(404);
    }

}
