package tests;

import base_urls.BookersBaseUrl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C33_XmlMapper extends BookersBaseUrl {

        /*
        Given
        1) Endpoint: https://restful-booker.herokuapp.com/booking
        2) Request Body:
            <booking>
                <firstname>Jim</firstname>
                <lastname>Brown</lastname>
                <totalprice>111</totalprice>
                <depositpaid>true</depositpaid>
                <bookingdates>
                    <checkin>2018-01-01</checkin>
                    <checkout>2019-01-01</checkout>
                </bookingdates>
                <additionalneeds>Breakfast</additionalneeds>
            </booking>

        When
        I send a POST request to the above URL

        Then
        1) The status code should be 200
        2) The response body should match the following structure:
           {
               "bookingid": 2243,
               "booking": {
                   "firstname": "John",
                   "lastname": "Doe",
                   "totalprice": 471,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2023-03-07",
                       "checkout": "2024-09-25"
                   },
                   "additionalneeds": "Lunch"
               }
           }
    */

    @Test
    void C33_XmlMapperTest() throws IOException {

        //Prepare the payload
        JsonNode payload = new XmlMapper().readTree(new File("src/test/resources/test_data/booking.xml"));
        //Rest is same

        //Send the request
        Response response = given(spec).body(payload).post("/booking");
        response.prettyPrint();

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