package com.example.rest_api_testing;

import org.testng.Assert;

import com.example.rest_api_testing.files.Payload;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {

        JsonPath js = new JsonPath(Payload.CoursePrice());

        // 1. Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);

        // 2.Print Purchase Amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmount);

        // 3. Print Title of the first course
        String titleFirstCourse = js.get("courses[0].title");
        System.out.println(titleFirstCourse);

        // 4. Print All course titles and their respective Prices
        for(int i=0;i<count;i++){
            String courseTitle = js.get("courses[" + i + "].title");
            int coursePrice = js.getInt("courses[" + i + "].price");
            System.out.println(courseTitle);
            System.out.println(coursePrice);
        }

        // 5. Print no of copies sold by RPA Course
        for(int i=0;i<count;i++){
            String courseTitle = js.get("courses[" + i + "].title");
            if(courseTitle.equalsIgnoreCase("RPA")){
                int copies = js.getInt("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }
        }

        // 6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for(int i=0;i<count;i++){
            int coursePrice = js.getInt("courses[" + i + "].price");
            int copies = js.getInt("courses[" + i + "].copies");
            sum += coursePrice * copies;
        }

        System.out.println(sum);

        Assert.assertEquals(sum, purchaseAmount);
    }
}
