package tests.booker_crud;

import base_urls.BookersBaseUrl;
import base_urls.BookersBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

public class GetBookingids extends BookersBaseUrl {
/*
Write an automation test to test all endpoints using the documentation available at:
https://restful-booker.herokuapp.com/apidoc/index.html.
*/

    @Test
    void GetBookingIdsTest() {

        //Send the request: https://restful-booker.herokuapp.com/booking
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