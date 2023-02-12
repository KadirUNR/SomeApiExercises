package ApiTests;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class SpartanTestsPOJODeserialization {

    @BeforeClass
    public void baseURI() {
        RestAssured.baseURI = "http://34.205.141.218:8000";
    }

    @Test
    public void test1() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}");

        //how to convert json to our spartan class
        Spartan spartan1 = response.body().as(Spartan.class);

        System.out.println(spartan1);

        Assert.assertEquals(spartan1.getName(), "Meta");
    }

    @Test
    public void jsonExample(){
        Gson gson = new Gson();

        String myJsonBody = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        // using gson method to de serialize our json body
        Spartan spartanMeta= gson.fromJson(myJsonBody,Spartan.class);

        //serialization Java object --> JSON BODY
        Spartan spartan = new Spartan(101, "Mike", "Male", 321342123l);

        //converting custom class to json
        String jsonbody = gson.toJson(spartan);

        System.out.println(jsonbody);


    }
}
