package day5.Task1;

import base_urls.GoRestUserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class T02_CreateUser extends GoRestUserBaseUrl {

    public static int userId;

    @Test
    public void createUser() {

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Sultan QA");
        requestBody.put("gender", "male");
        requestBody.put("email", "sultan" + System.currentTimeMillis() + "@gmail.com"); // ايميل فريد
        requestBody.put("status", "active");
        Response response = given(spec)
                .body(requestBody)
                .when()
                .post("/users");
        response.then()
                .statusCode(201) // 201 = Created
                .body("name", equalTo("Sultan QA"))
                .body("gender", equalTo("male"))
                .body("status", equalTo("active"));

        userId = response.jsonPath().getInt("id");
        System.out.println(" Created User ID: " + userId);
    }
}
