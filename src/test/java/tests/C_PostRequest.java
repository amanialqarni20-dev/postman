package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class C_PostRequest {

    @Test
    void createBookingTest() {

        // Prepare booking dates map
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-03-07");
        bookingDates.put("checkout", "2024-09-25");

        // Prepare payload
        Map<String, Object> payload = new HashMap<>();
        payload.put("firstname", "John");
        payload.put("lastname", "Doe");
        payload.put("totalprice", 15);
        payload.put("depositpaid", true);
        payload.put("bookingdates", bookingDates);
        payload.put("additionalneeds", "Lunch");

        // Send POST request
        Response response = given()
                .baseUri("https://restful-booker.herokuapp.com")
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/booking");

        // Print response for debugging
        response.prettyPrint();

        // Assertions
        response.then()
                .statusCode(200)
                .body("booking.firstname", equalTo("John"))
                .body("booking.lastname", equalTo("Doe"))
                .body("booking.totalprice", equalTo(15))
                .body("booking.depositpaid", equalTo(true))
                .body("booking.bookingdates.checkin", equalTo("2023-03-07"))
                .body("booking.bookingdates.checkout", equalTo("2024-09-25"))
                .body("booking.additionalneeds", equalTo("Lunch"))
                .body("bookingid", notNullValue()); // Just check it exists
    }
}
