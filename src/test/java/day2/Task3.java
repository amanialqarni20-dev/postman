package day2;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task3 {

    @Test
    public void example3Simple() {
        Response response = given()
                .baseUri("https://dummy.restapiexample.com/api/v1")
                .when()
                .get("/employees");

        assertEquals(response.getStatusCode(), 200);
        JsonPath jsonPath = response.jsonPath();

        List<String> employeeNames = jsonPath.getList("data.employee_name");
        System.out.println("Total employees: " + employeeNames.size());
        assertEquals(employeeNames.size(), 24);

        assertTrue(employeeNames.contains("Tiger Nixon"));
        assertTrue(employeeNames.contains("Garrett Winters"));

        List<Integer> ages = jsonPath.getList("data.employee_age").stream()
                .map(age -> Integer.parseInt(age.toString()))
                .collect(Collectors.toList());
        int maxAge = ages.stream().mapToInt(Integer::intValue).max().getAsInt();
        System.out.println("Highest age: " + maxAge);
        assertEquals(maxAge, 66);

        int minAge = ages.stream().mapToInt(Integer::intValue).min().getAsInt();
        String youngest = jsonPath.getString("data.find { it.employee_age == '" + minAge + "' }.employee_name");
        System.out.println("Youngest employee: " + youngest);
        assertEquals(youngest, "Tatyana Fitzpatrick");

        List<Integer> salaries = jsonPath.getList("data.employee_salary").stream()
                .map(sal -> Integer.parseInt(sal.toString()))
                .collect(Collectors.toList());
        int totalSalary = salaries.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Total salary: " + totalSalary);
        assertEquals(totalSalary, 6644770);
    }
}
