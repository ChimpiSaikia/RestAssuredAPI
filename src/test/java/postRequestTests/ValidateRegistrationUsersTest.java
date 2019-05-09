package postRequestTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.json.JsonException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateRegistrationUsersTest {

    @Test
    public void validateRegistrationOfUsersTest(){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "Application/json");
        JSONObject requestJson = new JSONObject();
        try {

            requestJson.put("email","sydney@fife");
            requestJson.put("password","pistol");

        }catch (JsonException exception){
            exception.printStackTrace();
        }

        request.body(requestJson.toString());
        Response response = request.post("https://reqres.in/api/register");
        System.out.println("Response Body "+response.getBody().prettyPrint());
        Assert.assertTrue(( response.getStatusCode() == 201));
    }

    @Test
    public void invalidRegistrationOfUsersTest(){
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "Application/json");
        JSONObject requestJson = new JSONObject();
        try {

            requestJson.put("email","sydney");

        }catch (JsonException exception){
            exception.printStackTrace();
        }

        request.body(requestJson.toString());
        Response response = request.post("https://reqres.in/api/register");
        Assert.assertTrue(( response.getStatusCode() == 400));
    }

//    Response getResponse(){
//        Response response = request.post("https://reqres.in/api/register");
//        System.out.println("Response Body "+response.getBody().prettyPrint());
//        return response;
//    }
}
