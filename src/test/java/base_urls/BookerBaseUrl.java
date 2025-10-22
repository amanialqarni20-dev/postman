package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class BookerBaseUrl {

    protected RequestSpecification spec;
    private static String baseUrl = "https://restful-booker.herokuapp.com";

    @BeforeMethod//Before each test method, this will work and initialize the spec object.
    public void setSpec() {
        spec = new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("Cookie", "token=" + getToken())
                .setContentType(ContentType.JSON)
                .build();
    }

    String getToken() {

        String credentials = """
                {
                    "username" : "admin",
                    "password" : "password123"
                }""";

        return given()
                .body(credentials)
                .contentType(ContentType.JSON)
                .post(baseUrl + "/auth")
                .jsonPath()
                .getString("token");
    }

}