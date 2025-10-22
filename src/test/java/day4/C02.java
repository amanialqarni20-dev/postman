package day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class C02 {

    @Test
    public void createPetWithJsonString() {

        // هنا حطينا الـ JSON كـ String
        String petJson = """
        {
            "id": 12345,
            "category": {
                "id": 1,
                "name": "Dogs"
            },
            "name": "Buddy",
            "photoUrls": [
                "https://example.com/dog.jpg"
            ],
            "tags": [
                {
                    "id": 1,
                    "name": "cute"
                }
            ],
            "status": "available"
        }
        """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(petJson)
                .when()
                .post("https://petstore.swagger.io/v2/pet");

        response.prettyPrint();

        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 201,
                "Status code is not successful");
    }
}
