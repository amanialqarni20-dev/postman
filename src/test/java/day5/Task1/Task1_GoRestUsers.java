package day5.Task1;

import base_urls.BookStoreBaseUrl;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static utilities.ObjectMapperUtils.getJsonNode;

public class Task1_GoRestUsers extends BookStoreBaseUrl {

    ObjectNode payload = (ObjectNode) getJsonNode("bookstoreUser");
    Faker faker = new Faker();

    @Test(priority = 1)
    void getAllUsers() {
        Response response = given(spec).get("/Account/v1/Users");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test(priority = 2)
    void createUser() {
        payload.put("userName", "user" + System.currentTimeMillis());
        payload.put("password", "Password123!");

        Response response = given(spec).body(payload).post("/Account/v1/User");
        response.prettyPrint();

        response.then().statusCode(201)
                .body("username", equalTo(payload.get("userName").textValue()));

        payload.put("userId", response.jsonPath().getString("userID"));
    }

    @Test(dependsOnMethods = "createUser", priority = 3)
    void getUser() {
        spec.pathParam("id", payload.get("userId").textValue());

        Response response = given(spec).get("/Account/v1/User/{id}");
        response.prettyPrint();

        response.then().statusCode(200)
                .body("username", equalTo(payload.get("userName").textValue()));
    }

    @Test(dependsOnMethods = "createUser", priority = 4)
    void updateUser() {
        spec.pathParam("id", payload.get("userId").textValue());

        payload.put("userName", "UpdatedUser" + System.currentTimeMillis());
        payload.put("password", "Password123!");

        Response response = given(spec).body(payload).put("/Account/v1/User/{id}");
        response.prettyPrint();

        response.then().statusCode(200)
                .body("username", equalTo(payload.get("userName").textValue()));
    }

    @Test(dependsOnMethods = "createUser", priority = 5)
    void updatePartialUser() {
        spec.pathParam("id", payload.get("userId").textValue());

        payload.put("userName", "PartialUpdate" + System.currentTimeMillis());

        Response response = given(spec).body(payload).patch("/Account/v1/User/{id}");
        response.prettyPrint();

        response.then().statusCode(200)
                .body("username", equalTo(payload.get("userName").textValue()));
    }

    @Test(dependsOnMethods = "createUser", priority = 6)
    void deleteUser() {
        spec.pathParam("id", payload.get("userId").textValue());

        Response response = given(spec).delete("/Account/v1/User/{id}");
        response.prettyPrint();

        response.then().statusCode(204);
    }

    @Test(dependsOnMethods = {"deleteUser", "createUser"}, priority = 7)
    void getUserNegative() {
        spec.pathParam("id", payload.get("userId").textValue());

        Response response = given(spec).get("/Account/v1/User/{id}");
        response.prettyPrint();

        response.then().statusCode(404)
                .body("message", equalTo("User not found."));
    }
}
