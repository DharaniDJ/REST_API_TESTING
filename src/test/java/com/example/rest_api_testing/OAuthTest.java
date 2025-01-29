package com.example.rest_api_testing;

import static io.restassured.RestAssured.given;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.example.rest_api_testing.pojo.Api;
import com.example.rest_api_testing.pojo.GetCourse;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class OAuthTest {

    /*
     * ******************************************************************

            Authorization Server EndPoint:

            https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token

            HTTP Method : POST

            Form parameters :

            client_id:

            692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com

            client_secret:  erZOWM9g3UtwNRj340YYaK_W

            grant_type:   client_credentials

            scope:  trust

            ******************************************************************

            GetCourseDetails EndPoint (Secured by OAuth) :

            https://rahulshettyacademy.com/oauthapi/getCourseDetails

            HTTP Method : GET

            Query Parameter : access_token
     */

     public static void main(String[] args) {

       RestAssured.baseURI = "https://rahulshettyacademy.com";

       String[] courses = {"Selenium Webdriver Java","Cypress","Protractor"};

       String response = given()
              .formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
              .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
              .formParams("grant_type", "client_credentials")
              .formParams("scope", "trust")
              .log().all()
       .when()
              .post("/oauthapi/oauth2/resourceOwner/token").asString();

       System.out.println(response);
       JsonPath js = new JsonPath(response);
       String accessToken = js.getString("access_token");

       GetCourse gc = given()
              .queryParam("access_token", accessToken)
              .log().all()
       .when()
              .get("/oauthapi/getCourseDetails").as(GetCourse.class);

       System.out.println(gc.getLinkedIn());
       System.out.println(gc.getInstructor());

       System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());

       List<Api> apiCourses = gc.getCourses().getApi();

       for(int i=0;i<apiCourses.size();i++) {
              if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
                     System.out.println(apiCourses.get(i).getPrice());
              }
       }

       // Get the course names of WebAutomation
       List<String> webAutomationCourses = gc.getCourses().getWebAutomation().stream().map(course -> course.getCourseTitle()).toList();
       System.out.println(webAutomationCourses);
       Assert.assertEquals(webAutomationCourses.toArray(), courses);
       Assert.assertEquals(webAutomationCourses, Arrays.asList(courses));
     }
}
