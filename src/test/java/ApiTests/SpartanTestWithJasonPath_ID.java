package ApiTests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class SpartanTestWithJasonPath_ID {

    @BeforeClass
    public void baseURI() {
        RestAssured.baseURI = "http://34.205.141.218:8000";
    }

    /*
    Given accept Type is Json
    And path param Spartan Id is 1
    When user sends a get request to: /api/spartans/{id}
    Then Status code is 200
    And content type is json
    And "id"    : 426,
        "name"  : Maja,
        "gender": Female,
        "phone" : 4218971349
     */

    @Test
    public void test1 (){


        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 426)
                .when().get("/api/spartans/{id}");

        int id = response.path("id");
        System.out.println("id = " + id);



         // how read value with jsonPath?

        JsonPath jsonData = response.jsonPath();

        int Id1 = jsonData.getInt("id");
        String name = jsonData.getString("name");
        String gender = jsonData.getString("gender");
        long phone = jsonData.getLong("phone");

        System.out.println("phone = " + phone);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

     assertEquals(Id1, 426);
     assertEquals(name, "Maja");
     assertEquals(gender, "Female");
     assertEquals(phone,4218971349L);


    }
}
