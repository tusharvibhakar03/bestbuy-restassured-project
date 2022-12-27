package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class StoresCURDTest extends TestBase {

    @Test
    public void getAllStoreInfo() {
        Response response = given()
                .when()
                .get("/stores");
        response.then().statusCode(200);
        response.prettyPrint();
    }
@Test
    public void getSingleStoreInfo(){
        Response response = given()
                .pathParam("id",4)
                .when()
                .get("/stores/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
@Test
    public void searchStoreWithParameter(){
        Map<String,Object> qParams = new HashMap<>();
        qParams.put("name", "Maplewood");
//        qParams.put("limit",2);
        Response response = given()
                .queryParams(qParams)
//                .queryParam("name","Fargo")
//                .queryParam("limit",2)
                .when()
                .get("/stores");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void deleteRecord(){
        Response response = given()
                .basePath("/stores")
                .pathParam("zip",56301)
                .when()
                .delete("/{zip}");
        response.then().statusCode(404);
        response.prettyPrint();

    }
    @Test
    public void patchRecord() {
        Response response = given()
                .basePath("/stores")
                .pathParam("zip", 56305)
                .when()
                .patch("/{zip},56310");
        response.then().statusCode(200);
        response.prettyPrint();

    }
    @Test
    public void createStore(){

        StorePojo storePojo = new StorePojo();

     //   StorePojo.setName("Southall");
        storePojo.setType("Retail");
        storePojo.setAddress("Near");
        storePojo.setAddress2("Broadway");
        storePojo.setCity("Southall");
       // storePojo.setState("Middlesex");
      //  storePojo.setZip("59204");
        storePojo.setLat(0);
        storePojo.setLng(0);
       // storePojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");

        Response response = (Response) given()
                .headers("Content-Type","application/json")
                .body(storePojo)
                .when()
                .post("/stores");
        response.then().statusCode(201);
        response.prettyPrint();

    }

    @Test
    public void updateStoreWithPatch() {

        StorePojo storePojo = new StorePojo();
        storePojo.setName("Minnetonka");
        storePojo.setType("BigBox");
        storePojo.setCity("Hopkins");
      //  storePojo.setZip("55305");


        Response response = given()
                .basePath("/stores")
                .header("Content-Type", "application/json")
                .pathParam("id", 8925)
                .body(storePojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }







}
