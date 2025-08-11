package com.test.practice.ex_04_RestAssured_HTTP_Methods.D.PATCH;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting012PATCHNONBddStyleTest {
    // PUT

    // token, booking ID - A
    // public void get_token(){ }
    // public void get_booking_id(){}

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

    @Test
    public void test_patch_non_bdd() {

        String bookingid = "202";
        String token = "90f07a839272e69";

        String payload = "{\n" +
                "    \"firstname\" : \"Pramod1\",\n" +
                "    \"lastname\" : \"Brown\"\n" +
                "}";

        r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/" + bookingid);
        r.contentType(ContentType.JSON);
        // r.header("Cookie","token="+token);
        r.cookie("token", token);
        r.body(payload).log().all();


        response = r.when().log().all().patch();

        vr = response.then().log().all();
        vr.statusCode(200);

        // we have not verified the response, we have only verified the status code.

    }
}
