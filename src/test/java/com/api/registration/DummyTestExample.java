package com.api.registration;

import static io.restassured.RestAssured.given;
import io.restassured.response.*;
import io.restassured.matcher.ResponseAwareMatcher;
import java.io.File;
import java.io.IOException;
import static org.hamcrest.CoreMatchers.equalTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;

public class DummyTestExample {

    @Test
    public void verifyResponseAttribute(){
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("/Users/ashishkumar/Desktop/ApiTestFramework/src/test/java/com/api/registration/DummyResp.json");
        String json = null;
        try {
            ObjectNode objectNode = mapper.readValue(file,ObjectNode.class);
            json = mapper.writeValueAsString(objectNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        given().baseUri("https://postman-echo.com").basePath("/get").queryParams("test",123)
                .when().get().then().cookie("sails.sid").header("Content-Encoding","gzip")
                .body(equalTo(json));
    }

    @Test
    public void verifyUrlInResponse(){
        given().baseUri("https://postman-echo.com").basePath("/get").queryParams("test",123)
                .when().get().then().cookie("sails.sid").header("Content-Encoding","gzip")
                .body("url",new ResponseAwareMatcher<Response>(){
                    public Matcher<?> matcher(Response response){
                        return equalTo("https://postman-echo.com/get?test="+response.path("args.test"));
                    }
                });
    }
}
