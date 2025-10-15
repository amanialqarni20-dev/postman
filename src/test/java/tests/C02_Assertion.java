package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class C02_Assertion {

    /*
        Given https://restful-booker.herokuapp.com/booking
        When User sends a GET Request to the url
        Then HTTP Status Code should be 200
        And Content Type should be JSON
        And Status Line should be HTTP/1.1 200 OK
    */

    @Test
    void assertionTest() {

        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        //response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);

        Assert.assertTrue(response.contentType().contains( "application/json"));

        Assert.assertEquals(response.statusLine(), "HTTP/1.1 200 OK");

    }

}