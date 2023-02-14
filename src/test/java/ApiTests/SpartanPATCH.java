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

public class SpartanPATCH {

    @BeforeClass
    public void baseURI() {
        RestAssured.baseURI = "http://34.205.141.218:8000";
    }

    @Test
    //2- Using Collection (Map)
    public void putRequest2(){
        Map<String,Object> patchMAP = new HashMap<>();
        patchMAP.put("name","MajaPATCHmap");


        // We gonna send body with updated value and content type header
        given().contentType(ContentType.JSON)
                .and().pathParam("id",426)
                .and().body(patchMAP)
                .when().patch("/api/spartans/{id}")
                .then().assertThat().statusCode(204);


    }


    }

