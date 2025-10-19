package tests;

import base_urls.JPHBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C15_PutRequest extends JPHBaseUrl {

    /*
        Given:
        1) URL: https://jsonplaceholder.typicode.com/todos/198
        2) Request Body:
           {
               "userId": 88,
               "title": "Do Your Homework",
               "completed": true
           }
        When:
        A PUT request is sent to the URL.

        Then:
        - The status code should be 200.
        - The response body should contain:
           "title", "completed", "id"
    */

    @Test
    void putRequestTest() {

        //Set the payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 88);
        payload.put("title", "Do Your Homework");
        payload.put("completed", true);
        System.out.println("payload = " + payload);

        //Send the request
        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(payload)
                .put("/todos/198");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo(payload.get("title")))
                .body("completed", equalTo(payload.get("completed")))
                .body("id", equalTo(198));
    }
}
