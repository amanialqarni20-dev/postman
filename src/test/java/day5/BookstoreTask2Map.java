package day5;
import base_urls.BookStoreBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;
public class BookstoreTask2Map extends BookStoreBaseUrl {
    String userId;
    String token;
    @Test(priority = 1)
    public void createOrUseUser() {
        // لو عندك يوزر مسبق
        Map<String, Object> userBody = new HashMap<>();
        userBody.put("userName", "user9360");
        userBody.put("password", "Test123!");

        Response response = given(spec)
                .contentType("application/json")
                .body(userBody)
                .when()
                .post("/Account/v1/User");

        if(response.statusCode() == 201) {
            userId = response.jsonPath().getString("userID");
            System.out.println("New user created: " + userId);
        } else {

            userId = "6a9d7f06-eb58-43a6-ad43-2f1131135d6c"; // حط الـ userID الصحيح
            System.out.println("Using existing user: " + userId);
        }
    }

    @Test(priority = 2)
    public void generateToken() {
        Map<String, Object> tokenBody = new HashMap<>();
        tokenBody.put("userName", "user9360");
        tokenBody.put("password", "Test123!");

        Response response = given(spec)
                .contentType("application/json")
                .body(tokenBody)
                .when()
                .post("/Account/v1/GenerateToken");

        response.prettyPrint();

        token = response.jsonPath().getString("token");
        assertEquals(response.jsonPath().getString("status"), "Success");
    }

    @Test(priority = 3)
    public void assignBookToUser() {
        Map<String, Object> bookMap = new HashMap<>();
        bookMap.put("userId", userId);

        Map<String, String>[] booksArray = new Map[1];
        Map<String, String> book1 = new HashMap<>();
        book1.put("isbn", "9781449325862"); // مثال
        booksArray[0] = book1;

        bookMap.put("collectionOfIsbns", booksArray);

        Response response = given(spec)
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .body(bookMap)
                .when()
                .post("/BookStore/v1/Books");

        response.prettyPrint();
        assertEquals(response.statusCode(), 201);
    }

    @Test(priority = 4)
    public void getUserInfo() {
        Response response = given(spec)
                .header("Authorization", "Bearer " + token)
                .pathParam("userID", userId)
                .when()
                .get("/Account/v1/User/{userID}");

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
    }

    @Test(priority = 5)
    public void getAllBooks() {
        Response response = given(spec)
                .when()
                .get("/BookStore/v1/Books");

        response.prettyPrint();
        assertEquals(response.statusCode(), 200);
    }
}
