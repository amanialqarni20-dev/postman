package tests;

import base_urls.CLBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.CLUserPojo;

import static io.restassured.RestAssured.given;

public class C22_CreateUserCL extends CLBaseUrl {
    //By using the document create a user.
    //https://documenter.getpostman.com/view/4012288/TzK2bEa8

    @Test
    void C22_CreateUserCLTest(){

        //Prepare the expected data
        CLUserPojo expectedData = new CLUserPojo("John","Doe","john.1235@yandex.com","John.123");

        //Send the request
        Response response = given(spec).body(expectedData).post("/users");
        response.prettyPrint();

        //



    }
}