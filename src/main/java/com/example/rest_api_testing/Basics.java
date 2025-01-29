package com.example.rest_api_testing;
// import static io.restassured.RestAssured.given;
// import static org.hamcrest.Matchers.equalTo;

// import io.restassured.RestAssured;
// import io.restassured.path.json.JsonPath;

public class Basics {
    public static void main(String[] args) {
        // RestAssured.baseURI = "https://rahulshettyacademy.com";
        // String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
        //     .body("{\n" + //
        //                         "  \"location\": {\n" + //
        //                         "    \"lat\": -38.383494,\n" + //
        //                         "    \"lng\": 33.427362\n" + //
        //                         "  },\n" + //
        //                         "  \"accuracy\": 50,\n" + //
        //                         "  \"name\": \"Frontline house\",\n" + //
        //                         "  \"phone_number\": \"(+91) 983 893 3937\",\n" + //
        //                         "  \"address\": \"29, side layout, cohen 09\",\n" + //
        //                         "  \"types\": [\n" + //
        //                         "    \"shoe park\",\n" + //
        //                         "    \"shop\"\n" + //
        //                         "  ],\n" + //
        //                         "  \"website\": \"http://google.com\",\n" + //
        //                         "  \"language\": \"French-IN\"\n" + //
        //                         "}").when().post("/maps/api/place/add/json")
        //                         .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
        //                         .header("Server","Apache/2.4.18 (Ubuntu)").extract().response().asString();

        //                         System.out.println(response);

        //                         JsonPath js = new JsonPath(response); // for parsing JSON
        //                         String placeId = js.getString("place_id");

        //                         // Add place -> Update Place with New Address -> Get Place to validate if New Address is present in response
    }
}
