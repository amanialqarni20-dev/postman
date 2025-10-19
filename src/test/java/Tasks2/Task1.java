package Tasks2;
import io.restassured.RestAssured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class Task1 {
    @Test
    public void verifyBookingDetails() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        given()
            .when()
                .get("/booking/32")
            .then()
                .statusCode(200)
                .contentType("application/json")
                .body("firstname", equalTo("Josh"))
                .body("lastname", equalTo("Allen"))
                .body("totalprice", equalTo(111));
    }
}
