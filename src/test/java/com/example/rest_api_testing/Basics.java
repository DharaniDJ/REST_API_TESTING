package com.example.rest_api_testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

import com.example.rest_api_testing.files.Payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Add place -> Update Place with New Address -> Get Place to validate if New Address is present in response

        // Add place
        String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
            .body(Payload.AddPlace()).when().post("/maps/api/place/add/json")
                                .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                                .header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();

        // String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
        // .body(new String(Files.readAllBytes(Paths.get("json-file-path")))).when().post("/maps/api/place/add/json")
        //                     .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
        //                     .header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response); // for parsing JSON
        String placeId = js.getString("place_id");
        System.out.println();
        System.out.println(placeId);

        // Update Place with New Address
        String newAddress = "70 Summer walk, USA";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
        .body("{\n" + //
                            "\"place_id\":\""+placeId+"\",\n" + //
                            "\"address\":\""+newAddress+"\",\n" + //
                            "\"key\":\"qaclick123\"\n" + //
                            "}").when().put("/maps/api/place/update/json")
                            .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));

        // Get Place to validate if New Address is present in response
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
        .when().get("/maps/api/place/get/json")
        .then().assertThat().log().all().statusCode(200).extract().response().asString();

        JsonPath js1 = new JsonPath(getPlaceResponse); // for parsing JSON
        String actualAddress = js1.getString("address");

        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddress);
    }
}

