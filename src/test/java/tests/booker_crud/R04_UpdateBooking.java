package tests.booker_crud;

import base_urls.BookerBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.annotations.Test;
import utilities.ObjectMapperUtils;

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
//        JsonNode payload = getJsonNode("booking");
//        updateJsonNode(payload, "firstname", "James"); //Instead of changing the existing json body, you can use a new json file.
//        updateJsonNode(payload, "lastname", "Brown");
//        updateJsonNode(payload, "totalprice", 111);
//        updateJsonNode(payload, "depositpaid", false);
//        updateJsonNode(payload.get("bookingdates"), "checkin", "2024-10-22");
//        updateJsonNode(payload.get("bookingdates"), "checkout", "2025-01-01");
//        updateJsonNode(payload, "additionalneeds", "Orange Juice");

        //OR:
        JsonNode payload = getJsonNode("booking_updated");
        System.out.println(payload);

        //Send the request
        // minutes later!


    }

}