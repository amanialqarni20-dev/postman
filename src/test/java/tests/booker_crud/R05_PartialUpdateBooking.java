package tests.booker_crud;

import base_urls.BookerBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static tests.booker_crud.R02_CreateBooking.bookingId;
import static utilities.ObjectMapperUtils.getJsonNode;

public class R05_PartialUpdateBooking extends BookerBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
            "firstname" : "Kevin",
            "lastname" : "Hulk"
        }
    When
        Send the patch request
    Then
        Status code should be 200
    And
        Response body should be like:
        {
            "firstname" : "Kevin",
            "lastname" : "Hulk",
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
    void partialUpdateBookingTest() {

        //Prepare the payload
        JsonNode payload = getJsonNode("booking_patch");

        //Send the request
        Response response = given(spec).body(payload).patch("/booking/" + bookingId);
        response.prettyPrint();

        //Do assertion
        JsonNode updatedJson = getJsonNode("booking_updated");//We can get previous json data in assertion
        response
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .body(
                        "firstname", equalTo(payload.get("firstname").asText()),
                        "lastname", equalTo(payload.get("lastname").asText()),
                        "totalprice", equalTo(updatedJson.get("totalprice").asInt())
                        10 minutes later!
                );


    }
}