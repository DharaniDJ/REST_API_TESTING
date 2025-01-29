package com.example.rest_api_testing;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import com.example.rest_api_testing.pojo.AddPlace;
import com.example.rest_api_testing.pojo.Location;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTest {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setLanguage("French-IN");
        addPlace.setName("Frontline house");
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setWebsite("http://google.com");

        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        addPlace.setTypes(types);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);

        RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://rahulshettyacademy.com")
            .addQueryParam("key", "qaclick123")
            .setContentType(ContentType.JSON)
            .build();

        ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(ContentType.JSON)
            .build();

        RequestSpecification req = given()
            .spec(requestSpec)
            .body(addPlace);

        Response response = req.when()
            .post("/maps/api/place/add/json")
        .then()
            .spec(responseSpec)
            .extract().response();

        String responseString = response.asString();
        System.out.println(responseString);
    }

}
