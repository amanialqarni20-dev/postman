package day3;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.Activity;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class Exercise3CRUDOperations {

    // Base URI و Content Type
    RequestSpecification spec = new RequestSpecBuilder()
            .setBaseUri("https://fakerestapi.azurewebsites.net")
            .setContentType(ContentType.JSON)
            .build();

    @Test
    public void testCRUDOperations() {
        // CREATE - POST
        Activity newActivity = new Activity(0, "New Activity", "2025-10-20T00:00:00", false);
        Response postResponse = given()
                .spec(spec)
                .body(newActivity)
                .post("/api/v1/Activities");

        System.out.println("POST Response:");
        postResponse.prettyPrint();
        assertEquals(postResponse.getStatusCode(), 200);

        Activity createdActivity = postResponse.as(Activity.class);
        int activityId = createdActivity.getId(); // ID اللي جت من السيرفر

        // READ - GET
        Response getResponse = given()
                .spec(spec)
                .get("/api/v1/Activities/" + activityId);

        System.out.println("GET Response:");
        getResponse.prettyPrint();
        assertEquals(getResponse.getStatusCode(), 200);

        Activity readActivity = getResponse.as(Activity.class);

        readActivity.setTitle("Updated Activity");
        readActivity.setCompleted(true);

        Response putResponse = given()
                .spec(spec)
                .body(readActivity)
                .put("/api/v1/Activities/" + activityId);

        System.out.println("PUT Response:");
        putResponse.prettyPrint();
        assertEquals(putResponse.getStatusCode(), 200);

        Response deleteResponse = given()
                .spec(spec)
                .delete("/api/v1/Activities/" + activityId);

        System.out.println("DELETE Response:");
        deleteResponse.prettyPrint();
        assertEquals(deleteResponse.getStatusCode(), 200);

        Response verifyDelete = given()
                .spec(spec)
                .get("/api/v1/Activities/" + activityId);

        System.out.println("VERIFY DELETE Response:");
        verifyDelete.prettyPrint();
        assertEquals(verifyDelete.getStatusCode(), 404);
    }
}
