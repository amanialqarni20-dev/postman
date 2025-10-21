package day3;

import base_urls.FakeRestBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.ActivityPojo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Ex3 extends FakeRestBaseUrl {

   /*
Task: Write code that performs all CRUD operations on "activities"
using the Fake REST API at https://fakerestapi.azurewebsites.net
Requirements:
1. Use POJO classes for all operations
2. Implement CREATE (POST) - Add new activity
3. Implement READ (GET) - Retrieve activity details
4. Implement UPDATE (PUT) - Modify existing activity
5. Implement DELETE - Remove activity
6. Add appropriate assertions for each operation
*/

    @Test
    public void testCRUDOperations() {

        ActivityPojo newActivity = new ActivityPojo(222, "Go to the gym", "2025-10-21T10:00:00Z", false);

        Response postResponse = given(spec)
                .contentType(ContentType.JSON)
                .body(newActivity)
                .when()
                .post("/Activities");
        postResponse.then()
                .statusCode(anyOf(is(200), is(201)))
                .body("title", equalTo("Go to the gym"));

        postResponse.prettyPrint();
        Response getResponse = given(spec)
                .when()
                .get("/Activities/1");

        getResponse.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", notNullValue());

        getResponse.prettyPrint();

        newActivity.setTitle("Go to the library");
        newActivity.setCompleted(true);

        Response putResponse = given(spec)
                .contentType(ContentType.JSON)
                .body(newActivity)
                .when()
                .put("/Activities/222");

        putResponse.then()
                .statusCode(anyOf(is(200), is(201)));

        putResponse.prettyPrint();

        Response deleteResponse = given(spec)
                .when()
                .delete("/Activities/222");

        deleteResponse.then()
                .statusCode(anyOf(is(200), is(204)));

        System.out.println("Delete request done");

        Response getAfterDelete = given(spec)
                .when()
                .get("/Activities/222");

        getAfterDelete.prettyPrint();
        System.out.println("Expected 404 as API doesn't persist data");
    }
}
