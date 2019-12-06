package com.api.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class DummyTest {

    @Test
    public void validateMetaData(){
        given()
                .baseUri("https://postman-echo.com")
                .basePath("/get")
                .queryParams("test",123)
                .when()
                .get()
                .then()
                .assertThat()
                .cookie("sails.sid")
                .statusCode(200)
                .header("Content-Encoding","gzip")
                .contentType(ContentType.JSON).log().all();

    }

    @Test
    public void validateResponseBody(){
        given()
                .baseUri("https://postman-echo.com")
                .basePath("/get")
                .queryParams("test",123)
                .when()
                .get()
                .then()
                .body("url", new ResponseAwareMatcher<Response>() {
                    @Override
                    public Matcher<?> matcher(Response response) throws Exception {
                        return equalTo("https://postman-echo.com/get?test="+response.path("args.test"));
                    }
                });
    }

    @Test
    public void validateCompleteResponseBody(){
        String expectedBody = null;
        File file = new File("/Users/ashishkumar/Desktop/ApiTestFramework/src/test/java/com/api/registration/DummyResp.json");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ObjectNode objectNode = objectMapper.readValue(file,ObjectNode.class);
            expectedBody = objectMapper.writeValueAsString(objectNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        given()
                .baseUri("https://postman-echo.com")
                .basePath("/get")
                .queryParams("test",123)
                .when()
                .get()
                .then()
                .body(equalTo(expectedBody))
                .log().all();

    }

    @Test
    public void verifyResponseContent(){
        given()
                .baseUri("https://postman-echo.com")
                .basePath("/get")
                .queryParams("test",123)
                .when()
                .get()
                .then()
                .body("headers.x-forwarded-port",equalTo(443));
    }

    @Test
    public void verifyResponseMatcher(){
                        given()
                                .baseUri("http://localhost:8888")
                                .basePath("/store")
                                .when()
                                .get()
                                .then()
                                .body("store.book.findAll {it.price < 10}.title",hasItems("Sayings of the Century", "Moby Dick"));
//        List<String> titles  = from(response.asString()).getList("store.book.findAll {it.price<10}.title");
//        System.out.println(titles);


//                .body("store.book.findAll {it.price < 10}.title",hasItems("Sayings of the Century", "Moby Dick"));


    }

    @Test
    public void verifyAnnonymous(){
        given().baseUri("http://localhost:8888").basePath("/anonymous").when().get().then().body("$",hasItems(1,2,3));
    }

    @Test
    public void responseTimeValidation(){
        long timeInMS = given()
                              .baseUri("https://postman-echo.com")
                              .basePath("/get")
                              .queryParams("test",123)
                              .when()
                              .get()
                              .time();
        System.out.println(timeInMS);
    }

    /*
      Deserialialization with Generics
     */






}
