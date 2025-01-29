package com.example.rest_api_testing.files;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";

        String response = given().header("Content-Type", "application/json")
        .body(Payload.addBook(isbn, aisle))
        .when().post("/Library/Addbook.php")
        .then().assertThat().statusCode(200)
        .extract().response().asString();

        JsonPath js = new JsonPath(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][]{
            {"abc", "123"},
            {"def", "456"},
            {"ghi", "789"}
        };
    }
}
