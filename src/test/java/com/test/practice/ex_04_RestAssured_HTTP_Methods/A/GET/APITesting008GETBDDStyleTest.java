package com.test.practice.ex_04_RestAssured_HTTP_Methods.A.GET;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class APITesting008GETBDDStyleTest {


    @Test
    public void test_GET_Request(){
        String pincode = "560048";
        RestAssured
                .given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/" + pincode)



                .when()
                .log()
                .all()
                .get()



                .then()
                .log().all()
                .statusCode(200);
    }


}
