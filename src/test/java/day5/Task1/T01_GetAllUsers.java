package day5.Task1;

import base_urls.GoRestUserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class T01_GetAllUsers extends GoRestUserBaseUrl {

    @Test
    public void getAllUsers() {
        Response response = given(spec)
                .when()
                .get("/users");
        response.then().statusCode(200); // 200 = OK
        System.out.println("All Users:");
        response.prettyPrint();
    }
}
