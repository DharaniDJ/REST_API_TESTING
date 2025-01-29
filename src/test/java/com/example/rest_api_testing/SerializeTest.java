package com.example.rest_api_testing;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import com.example.rest_api_testing.pojo.AddPlace;
import com.example.rest_api_testing.pojo.Location;

import io.restassured.RestAssured;

public class SerializeTest {

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

        String response = given()
            .log().all()
            .queryParam("key", "qaclick123")
            .body(addPlace)
        .when()
            .post("/maps/api/place/add/json")
        .then()
            .log().all()
            .assertThat()
            .statusCode(200).extract().response().asString();
    }

}
