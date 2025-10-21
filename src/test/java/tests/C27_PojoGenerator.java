package tests;

import base_urls.GoRestBaseUrl;
import org.testng.annotations.Test;

public class C27_PojoGenerator extends GoRestBaseUrl {
    /*
        Given https://gorest.co.in/public/v1/users
        When the user sends a GET request
        Then the HTTP status code should be 200
        And the last user should be as follows:
        {
          "id": 8194367,
          "name": "Gajadhar Tagore",
          "email": "tagore_gajadhar@cormier.example",
          "gender": "female",
          "status": "active"
        }
    */

    @Test
    void pojoGeneratorTest(){

        //Prepare the expected data




    }


}