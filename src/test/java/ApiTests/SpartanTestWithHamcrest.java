package ApiTests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
public class SpartanTestWithHamcrest {
    @BeforeClass
    public void baseURI() {
        RestAssured.baseURI = "http://34.205.141.218:8000";
    }

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",02)
                .when().get("/api/spartans/{id}")
                //response
                .then().statusCode(200)
                .and().assertThat().contentType("application/json");
    }

    @Test
    public void test2(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",02)
                .when().get("/api/spartans/{id}")
                //response
                .then().statusCode(200)
                .and().assertThat().contentType("application/json")
                .and().assertThat().body("id",equalTo(2),"name",equalTo("Nels"),"gender",
                        equalTo("Male"));


    }
    }

