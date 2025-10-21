package day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Ex1_Reqres_Map {

    /*
       Given: Base URL: https://reqres.in/api/users
       Request Body using Map:
       {
        "name": "morpheus",
        "job": "leader"
       }
       When: Send a POST request to the URL
       Then: Assert the status code is 201
       Verify the response body contains name and job
       Note: Add authentication header: "x-api-key" with value "reqres-free-v1"
    */

    @Test
    public void createUserWithMapTest() {

        // 1. Create request body as Map
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "morpheus");
        requestBody.put("job", "leader");

        // 2. Send POST request
        Response postResponse = given()
                .baseUri("https://reqres.in")
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(requestBody)
                .when()
                .post();

        // 3. Assert status code
        assertEquals(postResponse.getStatusCode(), 201);

        // 4. Print the response
        postResponse.prettyPrint();

        // 5. Extract response values and assert
        Map<String, String> responseMap = postResponse.as(Map.class);
        assertEquals(responseMap.get("name"), requestBody.get("name"));
        assertEquals(responseMap.get("job"), requestBody.get("job"));

        // Optional: GET request example (if API supports retrieving created user)
        // Response getResponse = given()
        //         .baseUri("https://reqres.in")
        //         .basePath("/api/users/" + responseMap.get("id"))
        //         .when()
        //         .get();
        // getResponse.prettyPrint();
    }
}
