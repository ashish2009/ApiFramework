package com.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.response.Response;
import java.io.FileReader;
import static io.restassured.RestAssured.given;

public class Assignment {

    public static void main(String[] args) {
        String url = "http://13.126.80.194:8080";
        String body = null;
        FileReader reader = null;
        String f = "/Users/ashishkumar/Desktop/ApiTestFramework/src/main/java/com/api/auth.json";
        try {
            reader = new FileReader(f);
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode object = objectMapper.readValue(reader,ObjectNode.class);
            body = objectMapper.writeValueAsString(object);
        } catch (Exception e) {

        }

        String token = given()
                .baseUri(url)
                .basePath("authenticate")
                .header("Content-Type","application/json")
                .body(body)
                .when()
                .post()
                .then()
                .extract()
                .path("token");

        String phone = given()
                .baseUri(url)
                .basePath("/api/v1/users")
                .header("Content-Type","application/json")
                .auth().preemptive().oauth2(token)
                .when()
                .get()
                .then()
                .extract()
                .path("[0].phone");


        Response response1 = given()
                .baseUri(url)
                .basePath("/api/v1/users/"+phone)
                .header("Content-Type","application/json")
                .auth().preemptive().oauth2(token)
                .when()
                .get()
                .then()
                .extract()
                .response();


        System.out.println(response1.asString());

    }
}
