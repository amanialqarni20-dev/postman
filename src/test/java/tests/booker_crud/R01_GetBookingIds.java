package tests.booker_crud;

import base_urls.BookersBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

public class R01_GetBookingIds extends BookersBaseUrl {
/*
Write an automation test to test all endpoints using the documentation available at:
https://restful-booker.herokuapp.com/apidoc/index.html.
*/
    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        Send get request
    Then
        Status code should be 200
    And
        Response body should contain bookingid field as not null.
     */

    @Test
    void GetBookingIdsTest() {

        //Send the request:
        Response response = given(spec).get("/booking");
        response.prettyPrint();

        //Do assertion
        response
                .then()
                .statusCode(200)
                .body(
                        "[0].bookingid", notNullValue()
                );
    }
}