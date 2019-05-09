package getRequestTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateResponseCodeTest {

    @Test
    public void validateResponseCodeTest(){

        int totalSize = 3;

        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        Assert.assertTrue((response.getStatusCode() == 200));
        System.out.println("Response Code *****" +response.getBody().prettyPrint());
        System.out.println("Header " +response.headers());
        System.out.println(response.getBody().prettyPrint());

        List<Map<String,?>> actualData = response.jsonPath().getList("data");
        System.out.println("Size is   "+actualData.size());

        List<Map<String,Object>> expectedDataList = getExpectedDataList();


        Assert.assertEquals(totalSize,actualData.size());

        for (int i=0; i<actualData.size(); i++){
            //System.out.println(actualData.get(i).toString());

            Assert.assertEquals(actualData.get(i).get("first_name"),expectedDataList.get(i).get("firstNameExpected"));
            Assert.assertEquals(actualData.get(i).get("last_name"),expectedDataList.get(i).get("lastNameExpected"));
            Assert.assertEquals(actualData.get(i).get("id"), expectedDataList.get(i).get("idExpected"));
        }

    }

    private List<Map<String, Object>> getExpectedDataList() {
        Map<String,Object> map1 = new HashMap<>();
        map1.put("idExpected",4);
        map1.put("firstNameExpected","Eve");
        map1.put("lastNameExpected", "Holt");

        Map<String,Object> map2 = new HashMap<>();
        map2.put("idExpected",5);
        map2.put("firstNameExpected","Charles");
        map2.put("lastNameExpected", "Morris");

        Map<String,Object> map3 = new HashMap<>();
        map3.put("idExpected",6);
        map3.put("firstNameExpected","Tracey");
        map3.put("lastNameExpected", "Ramos");

        List<Map<String, Object>> expectedDataList = new ArrayList<>();
        expectedDataList.add(map1);
        expectedDataList.add(map2);
        expectedDataList.add(map3);
        return expectedDataList;
    }
}
