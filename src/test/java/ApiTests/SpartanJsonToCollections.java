package ApiTests;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
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
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        //response.prettyPrint();

        //convert full json body to list Of Maps.

        List<Map<String,Object>> listOfSpartans = response.body().as(List.class);

        System.out.println(listOfSpartans.get(0));
        Map<String,Object> firstSpartan = listOfSpartans.get(0);
        System.out.println("firstSpartan = " + firstSpartan);

        System.out.println("firstSpartan.get(\"name\") = " + firstSpartan.get("name"));

        int counter =1;
        for (Map<String, Object> listOfSpartan : listOfSpartans) {

            System.out.println(counter+"-Spartan: "+listOfSpartan);
            counter++;

        }


    }

}
