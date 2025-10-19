package tests;

import base_urls.JPHBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class C12_PostRequestString extends JPHBaseUrl {

    @Test
    void postRequestStringTest() {

        // إنشاء الـ Request Body كـ String
        String requestBody = "{\n" +
                "   \"userId\": 55,\n" +
                "   \"title\": \"Tidy your room\",\n" +
                "   \"completed\": false\n" +
                "}";

        // إرسال POST request والتحقق من النتائج
        given()
                .spec(spec) // استخدام الـ Base URL من JPHBaseUrl
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/todos")
                .then()
                .statusCode(201) // تحقق من الـ status code
                .body("userId", equalTo(55))
                .body("title", equalTo("Tidy your room"))
                .body("completed", equalTo(false))
                .body("id", equalTo(201)); // تحقق من الـ id المتوقع
    }
}
