package day1;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PetStore_SoldPetsTest {
    @Test
    public void testSoldPets() {
        Response response =
            given()
                .baseUri("https://petstore.swagger.io/v2")
                .queryParam("status", "sold")
            .when()
                .get("/pet/findByStatus");
        response.then()
                .statusCode(200)
                .body("status", everyItem(equalTo("sold")))
                .body("name", hasItems("doggie", "fish"))
                .body("name", hasItem(containsString("og")))
                .body("$", hasSize(greaterThan(5)))
                .body("id", hasItem(greaterThan(1000)))
                .body("$", instanceOf(java.util.List.class))
                .body("name", hasItem(startsWith("d"))) // مثل doggie / dove / etc
                .body("status", everyItem(equalTo("sold")))
                .body("name", hasItem(anyOf(equalTo("doggie"), equalTo("fish"))));
        response.prettyPrint();
    }
}
