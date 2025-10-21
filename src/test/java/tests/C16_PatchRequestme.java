package tests;

import base_urls.JPHBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C16_PatchRequestme extends JPHBaseUrl {

    /*
        Given
            URL: https://jsonplaceholder.typicode.com/todos/198
            Body:
            {
            "title": "Study Lesson"
            }
        When
            Send PATCH request to the Url
        Then
            Status code is 200
        And response body is like
        {
        "completed": false,
        "title": "Study Lesson",
        "userId": 10,
        "id": 198
        }
    */

    @Test
    void patchRequestTest() {

        // Prepare payload
        Map<String, String> payload = new HashMap<>();
        payload.put("title", "Read Book");

        // Send PATCH request
        Response response = given()
                .spec(spec) // من JPHBaseUrl
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .patch("/todos/198");

        // Print response
        response.prettyPrint();

        // Assertions
        response.then()
                .statusCode(200)
                .body("title", equalTo("Read Book"))
                .body("completed", equalTo(true))
                .body("userId", equalTo(10))
                .body("id", equalTo(198));
    }
}
