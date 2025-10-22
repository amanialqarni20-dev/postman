package day5.Task1;

import base_urls.GoRestUserBaseUrl;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;
/*TASK1
    Get all users
    Create a user
    Get that user
    Update user
    Partial Update User
    Delete User
    Get User Negative
     */
public class GoRestCURD extends GoRestUserBaseUrl {

    ObjectNode payload = (ObjectNode) getJsonNode("goRestUser"); // جلب البيانات من JSON

    @Test(priority = 1)
    void getAllUsers() {
        Response response = given(spec).get("/users");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 2)
    void createUser() {
        // email فريد باستخدام timestamp
        payload.put("email", "sultan" + System.currentTimeMillis() + "@gmail.com");

        Response response = given(spec).body(payload).post("/users");
        response.prettyPrint();

        response.then().statusCode(201)
                .body("email", equalTo(payload.get("email").textValue()))
                .body("name", equalTo(payload.get("name").textValue()));

        payload.put("id", response.jsonPath().getInt("id"));
    }

    @Test(dependsOnMethods = "createUser", priority = 3)
    void getUser() {
        spec.pathParam("id", payload.get("id"));

        Response response = given(spec).get("/users/{id}");
        response.prettyPrint();

        response.then().statusCode(200)
                .body("email", equalTo(payload.get("email").textValue()))
                .body("name", equalTo(payload.get("name").textValue()));
    }

    @Test(dependsOnMethods = "createUser", priority = 4)
    void updateUser() {
        spec.pathParam("id", payload.get("id"));

        payload.put("name", "SultanUpdated");
        payload.put("email", "sultan" + System.currentTimeMillis() + "@gmail.com");
        payload.put("status", "inactive");
        payload.put("gender", "male");

        Response response = given(spec).body(payload).put("/users/{id}");
        response.prettyPrint();

        response.then().statusCode(200)
                .body("email", equalTo(payload.get("email").textValue()))
                .body("name", equalTo(payload.get("name").textValue()));
    }

    @Test(dependsOnMethods = "createUser", priority = 5)
    void updatePartialUser() {
        spec.pathParam("id", payload.get("id"));

        payload.put("name", "PartialUpdated");

        Response response = given(spec).body(payload).patch("/users/{id}");
        response.prettyPrint();

        response.then().statusCode(200)
                .body("email", equalTo(payload.get("email").textValue()))
                .body("name", equalTo(payload.get("name").textValue()));
    }

    @Test(dependsOnMethods = "createUser", priority = 6)
    void deleteUser() {
        spec.pathParam("id", payload.get("id"));

        Response response = given(spec).delete("/users/{id}");
        response.prettyPrint();

        response.then().statusCode(204);
    }

    @Test(dependsOnMethods = {"deleteUser","createUser"}, priority = 7)
    void getUserNegative() {
        spec.pathParam("id", payload.get("id"));

        Response response = given(spec).get("/users/{id}");
        response.prettyPrint();

        response.then().statusCode(404)
                .body("message", equalTo("Resource not found"));
    }
}
