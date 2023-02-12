package ApiTests;

import static io.restassured.RestAssured.*;

import com.beust.ah.A;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class SpartanJsonToCollections {

    @BeforeClass
    public void baseURI() {
        RestAssured.baseURI = "http://34.205.141.218:8000";
    }

    /*
    Given acceptType is Json,
    And path param Spartan Id is 11,
    When user sends request to /api/spartans/{id}
    Then statusCode is 200
    And contentType is Json
    "id": 11,
    "name": "Nona",
    "gender": "Female",
    "phone": 7959094216
     */

    @Test
    public void test1 (){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .when().get("api/spartans/{id}");

        // convert Json response to Java Collections(Map)

        Map <String,Object> spartanMap = response.body().as(Map.class);
        System.out.println(spartanMap.get("name"));
        System.out.println(spartanMap.get("id"));

        Assert.assertEquals(spartanMap.get("name"),"Nona");
        Assert.assertEquals(spartanMap.get("gender"),"Female");






    }
}
