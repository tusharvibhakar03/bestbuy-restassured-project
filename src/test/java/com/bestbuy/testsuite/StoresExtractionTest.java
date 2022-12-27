package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);


    }

    //1)Extract the limit
    @Test
    public void test001() {
        int limit =response.extract().path("limit");

        System.out.println("The value of limit is : " + limit);

    }

    // 2) Extract the total
    @Test
    public void test002() {
        int total  =response.extract().path("total");

        System.out.println("The value of limit is : " + total);

    }

    @Test
//3) Extract the name of 5th store
    public void test003() {
        String storeName = response.extract().path("data[4].name");

        System.out.println("The 5th store name is : " +storeName);

    }

    @Test
    //4)Extract the names of all the stores
    public void test004() {
        List<Integer> listOfstores = response.extract().path("data.name");
        System.out.println("List of stores are : " + listOfstores);

    }

    @Test
    //5)Extract the storeId of all the store
    public void test005() {
        List<Integer> listOfIds = response.extract().path("data.services.storeservices.storeId");
        System.out.println("List of store id : " + listOfIds);

    }
    @Test
    //6)Print the size of the data list
    public void test006() {
        List<HashMap<String,?>> storeData = response.extract().path("data");
        System.out.println("size of data :" + storeData.size());
    }
    @Test
    //7)Get all the zip of the store where store name = St Cloud

    public void test007() {
        List<Integer> zip = response.extract().path("data.findAll{it.name == 'St Cloud'}.zip");
        System.out.println("The zip of store name  is : " + zip);

    }

@Test
  //  8)Get the address of the store where store name = Rochester

public void test008() {
    List<Integer> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
    System.out.println("The address  of Rochester  is : " + address);


}

@Test
    //9)Get all the services of 8th store

public void test009() {
    List<Integer> services = response.extract().path("data[7].services");
    System.out.println("The services  of 8th Store are : " + services);

}
@Test
  // 10) Get storeservices of the store where service name = Windows Store
public void test010() {
    List<HashMap<String,?>> storeServices = response.extract().path("data.findAll{it.services.findAll{it.name == 'Windows Store'}}.services.storeservices");
    System.out.println("storeservices at store service name is Windows store :" + storeServices);
}
@Test
    //11)Get all the Id of all the store

public void test011() {
    List<Integer> ID = response.extract().path("data.id");
    System.out.println("The ID of All store are : " + ID);

}

@Test
    //12)Get all the storeId of all the store

public void test012() {
    List<Integer> storeID = response.extract().path("data.services.storeservices.storeId");
    System.out.println("The storeID of All store are : " + storeID);

}
@Test

    //13)Find the store names Where state = ND

public void test013() {
    List<Integer> storename = response.extract().path("data.findAll{it.state == 'ND'}.name");
    System.out.println("The store name of ND is : " + storename);

}
@Test
    //14)Find the Total number of services for the store where store name = Rochester
public void test014() {
    List<List<String>> services  = response.extract().path("data.findAll{it.name == 'Rochester'}.services.name");
     System.out.println("Total number of services of Rochester are  : " + services.size());

}
@Test
 //   15)Find the createdAt for all services whose name = “Windows Store”
public void test015() {
    List<Integer> createdAt = response.extract().path("data.findAll{it.name == 'Windows Store'}.createdAt");
    System.out.println("CreatedAt of Windows Store is  : " + createdAt);

}

@Test
    //16)Find the name of all services Where store name = “Fargo”

public void test016() {
    List<Integer> services = response.extract().path("data.findAll{it.name == 'Fargo'}.services");
    System.out.println("Services of Fargo Store are  : " + services);

}

@Test

    //17)Find the zip of all the store

public void test017() {
    List<Integer> zip = response.extract().path("data.zip");
    System.out.println("Zip of All Stores are  : " + zip);

}

@Test
  //  18)Find the zip of store name = Roseville
public void test018(){
List<Integer> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
    System.out.println("zip of Roseville Store is  : " + zip);

}

@Test
    //19)Find the storeservices details of the service name = Magnolia Home Theater

public void test019(){
    List<HashMap<String,?>> storeServices = response.extract().path("data.findAll{it.services.findAll{it.name == 'Magnolia Home Theater'}}.services.storeservices");
    System.out.println("storeservices at store service name is Magnolia Home Theater :" + storeServices);
}
@Test
    //20)Find the lat of all the stores

public void test020(){
    List<Integer> lat = response.extract().path("data.lat");
    System.out.println("lat of all stores  : " + lat);

}


}
