package day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserRequest;
import pojos.UserResponse;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Ex1 {

    /*
       Given: Base URL: https://reqres.in/api/users
       Request Body:
       {
        "name": "morpheus",
        "job": "leader"
       }
       When: Send a POST request to the URL
       Then: Assert the status code is 201
       Verify the response body matches the structure:
       {
        "name": "morpheus",
        "job": "leader",
        "id": "496",
        "createdAt": "2022-10-04T15:18:56.372Z"
       }
       Note: Add authentication header: "x-api-key" with value "reqres-free-v1"
    */

    @Test
    public void createUserTest() {

        // 1. Create request POJO
        UserRequest requestUser = new UserRequest("morpheus", "leader");

        // 2. Send POST request
        Response response = given()
                .baseUri("https://reqres.in")
                .basePath("/api/users")
                .contentType(ContentType.JSON)
                .header("x-api-key", "reqres-free-v1")
                .body(requestUser)
                .when()
                .post();

        // 3. Assert status code
        assertEquals(response.getStatusCode(), 201);

        // 4. Deserialize response to POJO
        UserResponse responseUser = response.as(UserResponse.class);

        // 5. Assertions on response body
        assertEquals(responseUser.getName(), requestUser.getName());
        assertEquals(responseUser.getJob(), requestUser.getJob());

        // Print the response nicely
        response.prettyPrint();
    }
}
