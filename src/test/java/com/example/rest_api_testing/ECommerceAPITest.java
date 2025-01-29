package com.example.rest_api_testing;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.rest_api_testing.pojo.LoginRequest;
import com.example.rest_api_testing.pojo.LoginResponse;
import com.example.rest_api_testing.pojo.OrderDetail;
import com.example.rest_api_testing.pojo.Orders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class ECommerceAPITest {

    public static void main(String[] args) {
        RequestSpecification req = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("test1234@asu.edu");
        loginRequest.setUserPassword("Test@123");

        RequestSpecification reqLogin = given().log().all()
            .spec(req)
            .body(loginRequest);

        LoginResponse loginResponse = reqLogin.when()
            .post("/api/ecom/auth/login")
        .then()
            .log().all()
            .extract()
            .response()
            .as(LoginResponse.class);

        System.out.println(loginResponse.getToken());
        String token = loginResponse.getToken();
        System.out.println(loginResponse.getUserId());
        String userId = loginResponse.getUserId();

        // Add a new product
        RequestSpecification addProductBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token)
                .build();

        RequestSpecification reqAddProduct = given().log().all()
            .spec(addProductBaseReq)
            .param("productName","Laptop")
            .param("productAddedBy",userId)
            .param("productCategory","Electronics")
            .param("productSubCategory","Dell")
            .param("productPrice","12500")
            .param("productDescription","Latitude")
            .param("productFor", "students")
            .multiPart("productImage", new File("//Users//dharanichinta//Desktop//test.png"));

        JsonPath js = reqAddProduct.when()
            .post("/api/ecom/product/add-product")
        .then()
            .log().all()
            .extract()
            .response()
            .jsonPath();

        String productId = js.getString("productId");
        System.out.println(productId);

        // Create order
        RequestSpecification createOrderBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token)
                .setContentType(ContentType.JSON)
                .build();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCountry("India");
        orderDetail.setProductOrderedId(productId);

        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        orderDetails.add(orderDetail);

        Orders orders = new Orders();
        orders.setOrders(orderDetails);

        RequestSpecification reqCreateOrder = given().log().all()
                .spec(createOrderBaseReq)
                .body(orders);

        JsonPath jsOrder = reqCreateOrder.when()
            .post("/api/ecom/order/create-order")
        .then()
            .log().all()
            .extract()
            .response()
            .jsonPath();

        String orderId = jsOrder.getString("orders[0]");
        System.out.println(orderId);


        // Delete product
        RequestSpecification deleteProductBase = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token)
                .setContentType(ContentType.JSON)
                .build();

        RequestSpecification reqDeleteProduct = given().log().all()
                .spec(deleteProductBase)
                .pathParam("productId", productId);

        String deleteProductResponse = reqDeleteProduct.when()
            .delete("/api/ecom/product/delete-product/{productId}")
        .then()
            .log().all()
            .extract()
            .response()
            .asString();

        JsonPath jsDeleteProduct = new JsonPath(deleteProductResponse);
        String deleteProductStatus = jsDeleteProduct.getString("message");
        System.out.println(deleteProductStatus);
    }
}
