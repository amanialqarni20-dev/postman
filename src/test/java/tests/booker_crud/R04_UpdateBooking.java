package tests.booker_crud;

import base_urls.BookerBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static tests.booker_crud.R02_CreateBooking.bookingId;
import static utilities.ObjectMapperUtils.getJsonNode;
import static utilities.ObjectMapperUtils.updateJsonNode;

public class R04_UpdateBooking extends BookerBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
            "firstname" : "James",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
    When
        Send put request
    Then
        Status code should be 200
    And
        Response body should be like:
        {
            "firstname" : "James",
            "lastname" : "Brown",
            "totalprice" : 111,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Breakfast"
        }
     */

    @Test
    void R04_UpdateBookingTest() {

        //Prepare the payload
        JsonNode payload = getJsonNode("booking");
        updateJsonNode(payload, "firstname", "James");
        updateJsonNode(payload, "lastname", "Brown");
        updateJsonNode(payload, "totalprice", 111);
        updateJsonNode(payload, "depositpaid", false);
        updateJsonNode(payload.get("bookingdates"), "checkin", "2024-10-22");
        updateJsonNode(payload.get("bookingdates"), "checkout", "2025-01-01");
        updateJsonNode(payload, "additionalneeds", "Orange Juice");
        //Instead of changing the existing json body, you can use a new json file.

        //OR:
        payload = getJsonNode("booking_updated");
        //System.out.println(payload.toPrettyString());

        //Send the request
        Response response = given(spec)
                .body(payload)
                .put("/booking/" + bookingId);
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body(
                        "firstname", equalTo(payload.get("firstname").asText()),
                        "lastname", equalTo(payload.get("lastname").asText()),
                        "totalprice", equalTo(payload.get("totalprice").asInt()),
                        "depositpaid", equalTo(payload.get("depositpaid").asBoolean()),
                        "bookingdates.checkin", equalTo(payload.get("bookingdates").get("checkin").asText()),
                        "bookingdates.checkout", equalTo(payload.get("bookingdates").get("checkout").asText()),
                        "additionalneeds", equalTo(payload.get("additionalneeds").asText())
                );
    }
}
