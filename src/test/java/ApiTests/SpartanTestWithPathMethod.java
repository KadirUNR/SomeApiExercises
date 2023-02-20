package ApiTests;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SpartanTestWithPathMethod {
    @BeforeClass
    public void baseURI() {
        RestAssured.baseURI = "http://34.205.141.218:8000";
    }

    @Test
    public void test1 (){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", "11")
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json");

        System.out.println("id:" + response.body().path("id").toString());
        System.out.println("name:" + response.body().path("name").toString());
        System.out.println("gender:" + response.body().path("gender").toString());
        System.out.println("phone:" + response.body().path("phone").toString());

    }




    @Test
    public void test2(){

        Response response = get("/api/spartans");

        // extract firdt Id
        int firstId = response.path("id[0]");
        System.out.println(firstId);

        // extract first name
        String first1name = response.path("name[0]");
        System.out.println(first1name);

        // get the last firstname
        String last1name = response.path("name[-1]");
        System.out.println(last1name);

        // extract all firstnames and print them
        List<String> names = response.path("name");
        System.out.println("names = " + names);
        int numberOfSpartans = names.size();
        System.out.println("numberOfSpartans = " + numberOfSpartans);

        // extract all phoneNumbers and print them
        List<Object> phoneNumbers = response.path("phone");
        for (Object phoneNumber : phoneNumbers) {
            System.out.println(phoneNumber);
        }




    }
}
