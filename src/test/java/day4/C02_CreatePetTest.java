package day4;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojos.Pet;
import pojos.Category;
import pojos.Tag;
import java.util.Arrays;
import static io.restassured.RestAssured.given;
public class C02_CreatePetTest {
    @Test
    public void createPetTest() {
        Category category = new Category(1, "Dogs");
        Tag tag1 = new Tag(1, "cute");
        Pet pet = new Pet();
        pet.setId(12345);
        pet.setName("Buddy");
        pet.setCategory(category);
        pet.setPhotoUrls(Arrays.asList("https://example.com/dog.jpg"));
        pet.setTags(Arrays.asList(tag1));
        pet.setStatus("available");
        Response response = given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 201,
                "Status code is not successful!");
        Pet returnedPet = response.as(Pet.class);
        Assert.assertEquals(returnedPet.getId(), pet.getId());
        Assert.assertEquals(returnedPet.getName(), pet.getName());
        Assert.assertEquals(returnedPet.getCategory().getName(), pet.getCategory().getName());
        Assert.assertEquals(returnedPet.getPhotoUrls().get(0), pet.getPhotoUrls().get(0));
        Assert.assertEquals(returnedPet.getTags().get(0).getName(), pet.getTags().get(0).getName());
        Assert.assertEquals(returnedPet.getStatus(), pet.getStatus());
    }
}
