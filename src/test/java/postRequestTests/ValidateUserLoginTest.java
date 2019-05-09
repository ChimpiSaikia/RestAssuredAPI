package postRequestTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.json.JsonException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateUserLoginTest {
    @Test
    public void validateUsersLoginTest(){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "Application/json");
        JSONObject requestJson = new JSONObject();
        try {

            requestJson.put("email","peter@klaven");
            requestJson.put("password","cityslicka");

        }catch (JsonException exception){
            exception.printStackTrace();
        }

        request.body(requestJson.toString());
        Response response = request.post("https://reqres.in/api/login");
        System.out.println("Response Body "+response.getBody().prettyPrint());
        Assert.assertTrue(( response.getStatusCode() == 200));
    }
}
