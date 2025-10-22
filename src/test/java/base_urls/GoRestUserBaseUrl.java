package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class GoRestUserBaseUrl {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp() {
        spec = new RequestSpecBuilder()
                .setBaseUri("https://gorest.co.in/public/v2")
                .setContentType(ContentType.JSON)

                .addHeader("Authorization", "Bearer ced6577eb0ec0cd10510581a4eb5e35fdced73197f6a2f1c977d7493f8ac20f9")
                .build();
    }
}
