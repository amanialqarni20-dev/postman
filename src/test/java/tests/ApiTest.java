package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class ApiTest {
 public static void main(String[] args) {
     Response response = RestAssured.get("https://reqres.in/api/users");

     response.then().statusCode(200);
     response.then().assertThat().header("Content-Type", containsString("application/json"));
     response.then().assertThat().body("data", notNullValue());
 }
}
