package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class C_Post_Todos {

    @Test
    void postTodosTest() {

        // Prepare Payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 55);
        payload.put("title", "Tidy your room");
        payload.put("completed", false);

        // Send POST Request
        Response response = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/todos");

        // Print Response
        response.prettyPrint();

        // Assertions
        response.then()
                .statusCode(201)
                .body("userId", equalTo(55))
                .body("title", equalTo("Tidy your room"))
                .body("completed", equalTo(false))
                .body("id", notNullValue()); // JSONPlaceholder generates id dynamically
    }
}
