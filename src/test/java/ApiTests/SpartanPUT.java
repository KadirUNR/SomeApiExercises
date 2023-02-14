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

public class SpartanPUT {

    @BeforeClass
    public void baseURI() {
        RestAssured.baseURI = "http://34.205.141.218:8000";
    }
    // Three ways to sen json body
    //1- String
    //2- Using Collection (Map)
    //3- POJO


    @Test
    //1- String (We dont prefer)
    public void putRequest1(){
       given().contentType(ContentType.JSON)
                .and().pathParam("id", 426)
                .and().body("{\n" +
                        "    \"name\": \"MajaPUT\",\n" +
                        "    \"gender\": \"Female\",\n" +
                        "    \"phone\": 4218971349\n" +
                        "}")
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);

    }


    @Test
    //2- Using Collection (Map)
    public void putRequest2(){
        Map<String,Object> putMAP = new HashMap<>();
        putMAP.put("name","MajaPUTmap");
        putMAP.put("gender","Female");
        putMAP.put("phone",9876543210L);

        // We gonna send body with updated value and content type header
        given().contentType(ContentType.JSON)
                .and().pathParam("id",426)
                .and().body(putMAP)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);


    }

    @Test
    //3- POJO
    public void putRequest3(){
      Spartan spartan = new Spartan();
      spartan.setName("MajaPUTpojo");
      spartan.setGender("Female");
      spartan.setPhone(1234567890L);

        // We gonna send body with updated value and content type header
        given().contentType(ContentType.JSON)
                .and().pathParam("id",426)
                .and().body(spartan)
                .when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204);


    }






}
