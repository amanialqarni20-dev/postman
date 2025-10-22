package day5;

import base_urls.BookStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class BookstoreTask2JSONFiles extends BookStoreBaseUrl {

    String userId;
    String token;

    // Helper method to read JSON file
    private String readJson(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            throw new RuntimeException("Error reading file: " + filePath, e);
        }
    }

    @Test
    public void createUser() {
        String body = readJson("src/test/resources/test_data/createUser.json");

        Response response = given(spec)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/Account/v1/User");

        response.prettyPrint();

        userId = response.jsonPath().getString("userID");
        assertNotNull(userId, "userID is null!");
    }

    @Test
    public void generateToken() {
        String body = readJson("src/test/resources/test_data/generateToken.json");

        Response response = given(spec)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/Account/v1/GenerateToken");

        response.prettyPrint();

        token = response.jsonPath().getString("token");
        assertEquals(response.jsonPath().getString("status"), "Success", "Token generation failed!");
    }

    @Test
    public void assignBookToUser() {
        String baseBody = readJson("src/test/resources/test_data/assignBook.json");

        String finalBody = "{ \"userId\": \"" + userId + "\", " + baseBody.substring(1);

        Response response = given(spec)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(finalBody)
                .when()
                .post("/BookStore/v1/Books");

        response.prettyPrint();
        assertEquals(response.statusCode(), 201);
    }

    @Test
    public void getUserInfo() {
        Response response = given(spec)
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userId)
                .when()
                .get("/Account/v1/User/{userID}");

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getAllBooks() {
        Response response = given(spec)
                .when()
                .get("/BookStore/v1/Books");

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
    }
}
