package com.test.practice.ex_04_RestAssured_HTTP_Methods.A.GET;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting009HEADNonBDDStyleTest {

    RequestSpecification r; // pre request - given part
    Response response; // making the request - when
    ValidatableResponse vr;  // post request - then
    String pincode;

    @Test
    public void test_HEAD_NonBDD() {
        pincode = "560048";

        // Part 1
        r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/" + pincode);

        // Part 2
        response = r.when().log().all().head();

        // Part 3
        vr = response.then().log().all();
        vr.statusCode(200);


    }


}
