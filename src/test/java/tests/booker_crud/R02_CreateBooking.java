package tests.booker_crud;

import base_urls.BookerBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;

public class R02_CreateBooking extends BookerBaseUrl {

    public static int bookingId;

    @Test
    void createBookingTest() {

        //Prepare the payload
        JsonNode payload = getJsonNode("booking");

        //Send the request
        Response response = given(spec).body(payload).post("/booking");
        response.prettyPrint();
        bookingId = response.jsonPath().getInt("bookingid");
        System.out.println("Created Booking ID: " + bookingId);

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body(
                        "booking.firstname", equalTo(payload.get("firstname").asText()),
                        "booking.lastname", equalTo(payload.get("lastname").asText()),
                        "booking.totalprice", equalTo(payload.get("totalprice").asInt()),
                        "booking.depositpaid", equalTo(payload.get("depositpaid").asBoolean()),
                        "booking.bookingdates.checkin", equalTo(payload.get("bookingdates").get("checkin").asText()),
                        "booking.bookingdates.checkout", equalTo(payload.get("bookingdates").get("checkout").asText()),
                        "booking.additionalneeds", equalTo(payload.get("additionalneeds").asText())
                );
    }
}
