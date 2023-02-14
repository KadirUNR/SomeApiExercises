package ApiTests;

import com.google.gson.Gson;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

public class SpartanPostRequests {

    @BeforeClass
    public void baseURI() {
        RestAssured.baseURI = "http://34.205.141.218:8000";
    }
    @Test
    public void PostWithString(){
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body("{\n" +
                        "        \"name\": \"Maja\",\n" +
                        "        \"gender\": \"Female\",\n" +
                        "        \"phone\": 4218971349\n" +
                        "    }")
                .when().post("/api/spartans");
       // response.prettyPrint();
        assertEquals(response.contentType(), "application/json");
        assertEquals(response.statusCode(), 201);

        JsonPath jsonPath = response.jsonPath();
        assertEquals(jsonPath.getString("data.name"), "Maja");
        assertEquals(jsonPath.getString("data.gender"),"Female");
        assertEquals(jsonPath.getLong("data.phone"),4218971349L);

    }
    @Test
    public void PostMethodWithMAP(){
        Map<String,Object> requestMAP = new HashMap<>();
        requestMAP.put("name","MajaMAP");
        requestMAP.put("gender","Female");
        requestMAP.put("phone",4218971349L);

        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(requestMAP)
                .when().post("/api/spartans");


        assertEquals(response.statusCode(),201);
        response.prettyPrint();
        System.out.println("=====================================================");

        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.statusCode() = " + response.statusCode());

    }

    @Test
    public void PostWithPOJO(){

        // Create a Spartan object used as a body for post request

        Spartan spartan = new Spartan();
        spartan.setName("PojoMaja");
        spartan.setGender("Female");
        spartan.setPhone(1234567890L);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .and().body(spartan)
                .when().post("/api/spartans");

        assertEquals(response.contentType(),"application/json");
        assertEquals(response.statusCode(),201);


        response.prettyPrint();
        System.out.println("================================================================");
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.statusCode() = " + response.statusCode());


    }

}
