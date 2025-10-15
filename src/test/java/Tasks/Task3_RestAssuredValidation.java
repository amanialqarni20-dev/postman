package Tasks;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Task3_RestAssuredValidation {

    @Test
    void validateApiResponse() {
        Response response = RestAssured.get("https://fakerestapi.azurewebsites.net/api/v1/Users");
        response.prettyPrint();
        int statusCode = response.statusCode();
        System.out.println("Status Code: " + statusCode);
        Assert.assertEquals(statusCode, 200);
        String contentType = response.getContentType();
        System.out.println("Content-Type: " + contentType);
        Assert.assertTrue(contentType.contains("application/json"));


        String serverHeader = response.header("Server");
        System.out.println("Server: " + serverHeader);
        Assert.assertTrue(serverHeader.contains("Kestrel"));


        String transferEncoding = response.header("Transfer-Encoding");
        System.out.println("Transfer-Encoding: " + transferEncoding);
        Assert.assertEquals(transferEncoding, "chunked");

    }
}