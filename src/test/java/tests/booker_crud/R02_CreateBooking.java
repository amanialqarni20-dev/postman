package tests.booker_crud;

import base_urls.BookersBaseUrl;
import org.testng.annotations.Test;

public class R02_CreateBooking extends BookersBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    And
        {
            "firstname" : "Jim",
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
        Send post request
    Then
        Status cose should be 200
    And
        Response body should be like:
        {
            "bookingid": 1,
            "booking": {
                "firstname": "Jim",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
        }
     */

    @Test
    void createBookingTest(){



    }



}