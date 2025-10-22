package day4;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import pojos.RandomUserResponse;
import pojos.Result;

import static io.restassured.RestAssured.given;

public class C01_RandomUserApiTest {

    @Test
    public void validateRandomUserData() throws Exception {

       // GET
        Response response = given()
                .when()
                .get("https://randomuser.me/api");

        response.then().statusCode(200);

        // Deserialize
        ObjectMapper mapper = new ObjectMapper();
        RandomUserResponse randomUser = mapper.readValue(response.asString(), RandomUserResponse.class);

        Result user = randomUser.getResults().get(0);

        System.out.println("Random User");
        System.out.println("Email: " + user.getEmail());
        System.out.println("Username: " + user.getLogin().getUsername());
        System.out.println("Password: " + user.getLogin().getPassword());
        System.out.println("Medium Picture URL: " + user.getPicture().getMedium());


        Assert.assertNotNull(user.getEmail(), "Email is null!");
        Assert.assertNotNull(user.getLogin().getUsername(), "Username is null!");
        Assert.assertNotNull(user.getLogin().getPassword(), "Password is null!");
        Assert.assertNotNull(user.getPicture().getMedium(), "Medium picture is null!");
    }
}
