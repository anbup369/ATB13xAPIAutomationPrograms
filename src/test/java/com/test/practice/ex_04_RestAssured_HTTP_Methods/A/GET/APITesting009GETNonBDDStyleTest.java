package com.test.practice.ex_04_RestAssured_HTTP_Methods.A.GET;
/*
-----------------------------------------------------------------------------------
|                                   Test Summary                                  |
-----------------------------------------------------------------------------------
|           Test Name              | Input    | Expected Status | Purpose         |
| test_GET_NonBDD_Valid            | "560048" | 200 OK          | Valid pincode   |
| test_GET_NonBDD_Negative_Invalid | "@"      | 404 Not Found   | Invalid pincode |
-----------------------------------------------------------------------------------
 */
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting009GETNonBDDStyleTest {

    RequestSpecification r; // pre request - given part
    Response response; // making the request - when
    ValidatableResponse vr;  // post request - then
    String pincode;

    @Test
    public void test_GET_NonBDD_Valid() {
        pincode = "560048";

        // Part 1
        r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);

        // Part 2
        response = r.when().log().all().get();

        // Part 3
        vr = response.then().log().all();
        vr.statusCode(200);


    }


    @Test
    public void test_GET_NonBDD_Negative_Invalid(){
        pincode =  "@";
        // GIVEN
        r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);

        // WHEN
        response = r.when().log().all().get();

        System.out.println(response.asString());

        // THEN
        vr = response.then().log().all();
        vr.statusCode(404);
    }
}
