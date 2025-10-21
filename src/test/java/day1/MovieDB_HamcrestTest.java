package day1;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class MovieDB_HamcrestTest {
    @Test
    public void testPopularTVShows() {
        Response response = 
            given()
                .baseUri("https://api.themoviedb.org/3")
                .queryParam("api_key", "2eeebe74d17da380e718f9066997a62a")  // Query Parameter
            .when()
                .get("/tv/popular");
        response.then()
                .statusCode(200)
                .body("page", equalTo(1))
                .body("results.name", hasItems("Peacemaker", "One-Punch Man"))
                .body("results[0].overview", containsString("Ed Gein"))
                .body("results", hasSize(20)) //  hasSize Matcher
                .body("results.find { it.name == 'Game of Thrones' }.vote_count", greaterThan(25000))
                .body("results", instanceOf(java.util.List.class));
        response.prettyPrint();
    }
}
