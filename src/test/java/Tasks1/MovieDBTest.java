package Tasks1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MovieDBTest {

    @Test
    void testPopularTVShows() {

        RestAssured.baseURI = "https://api.themoviedb.org/3";

        Response response = given()
                .queryParam("api_key", "2eeebe74d17da380e718f9066997a62a")
                .when()
                .get("/tv/popular")
                .then()
                .statusCode(200)
                .body("page", equalTo(1)) // 1- التحقق من رقم الصفحة
                .body("results.name", hasItems("Peacemaker", "One-Punch Man")) // 2- التحقق من وجود المسلسلات
                .body("results[0].overview", containsString("Ed Gein")) // 3- أول مسلسل يحتوي على Ed Gein
                .body("results", hasSize(20)) // 4- عدد النتائج 20
                .body("results.find { it.name == 'Game of Thrones' }.vote_count", greaterThan(25000)) // 5- التصويتات
                .body("results", instanceOf(java.util.List.class)) // 6- تأكيد أنها List
                .extract().response();

        response.prettyPrint();
    }
}
