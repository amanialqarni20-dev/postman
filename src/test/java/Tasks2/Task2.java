package Tasks2;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.assertTrue;
public class Task2 {
    @Test
    public void todosJSONPathExample() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = RestAssured.get("/todos");
        response.then().statusCode(200);
        JsonPath jsonPath = response.jsonPath();
        List<Integer> idsOver190 = jsonPath.getList("findAll { it.id > 190 }.id");
        System.out.println("IDs > 190: " + idsOver190);
        List<Integer> userIdsUnder5 = jsonPath.getList("findAll { it.id < 5 }.userId");
        System.out.println("UserIds with IDs < 5: " + userIdsUnder5);
        List<String> titles = jsonPath.getList("title");
        assertTrue(titles.contains("quis eius est sint explicabo"), "Title not found!");
        List<Integer> idsForTitle = jsonPath.getList("findAll { it.title == 'quo adipisci enim quam ut ab' }.id");
        System.out.println("ID for title 'quo adipisci enim quam ut ab': " + idsForTitle);
    }
}
