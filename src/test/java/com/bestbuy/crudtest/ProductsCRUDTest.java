package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ProductsCRUDTest extends TestBase {

    @Test
    public void getAllProductsInfo() {
        Response response = given()
                .when()
                .get("/products");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getSingleProductInfo() {
        Response response = given()
                .pathParam("id", 43900)
                .when()
                .get("/products/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void searchProductWithParameter() {
        Map<String, Object> qParams = new HashMap<>();
        qParams.put("manufacturer","Duracell");
    //    qParams.put("limit",2);
        Response response = given()
                /*.queryParam("manufacturer","description"")
                .queryParam("limit",2)*/
                .queryParams(qParams)
                .when()
                .get("/products");
        response.prettyPrint();
        response.then().statusCode(200);
    }
@Test

public void createProduct() {

    List<String> courseList = new ArrayList<>();
    courseList.add("Radip");

    ProductPojo productPojo = new ProductPojo();
    productPojo.setName("National Panasonic)");
    productPojo.setType("stereo");
    productPojo.setManufacturer("National");


    Response response = given().log().all()
            .header("Content-Type", "application/json")
            .when()
            .body(productPojo)
            .post();
    response.prettyPrint();
    response.then().log().all().statusCode(201);
}

    @Test
    public void deleteRecord(){
        Response response = given()
                .basePath("/products")
                .pathParam("price",5.49)
                .when()
                .delete("/{price}");
        response.then().statusCode(404);
        response.prettyPrint();

    }
    @Test
    public void patchRecord() {
        Response response = given()
                .basePath("/products")
                .pathParam("price", 5.49)
                .when()
                .patch("/{price},6.49");
        response.then().statusCode(200);
        response.prettyPrint();


    }}


































