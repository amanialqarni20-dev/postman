package day5.Task1;

import base_urls.GoRestUserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class T03_GetUser extends GoRestUserBaseUrl {

    @Test
    public void getUser() {
        Response response = given(spec)
                .when()
                .get("/users/8198671");
        response.then()
                .statusCode(200)
                .body("id", equalTo(8198671))
                .body("name", notNullValue())
                .body("email", containsString("@"))
                .body("status", anyOf(equalTo("active"), equalTo("inactive")));
        response.prettyPrint();
    }
}
