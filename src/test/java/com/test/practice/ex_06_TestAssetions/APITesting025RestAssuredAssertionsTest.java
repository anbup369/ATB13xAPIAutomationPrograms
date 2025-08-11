package com.test.practice.ex_06_TestAssetions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;


public class APITesting025RestAssuredAssertionsTest {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    Integer bookingID;


    @Test
    public void test_createBooking_POST() {

        // payload
        // given - setting up the body, url, base path, uri
        // when  - making the req
        // then - extraction
        String request_payload = "{\n" +
                "        \"firstname\": \"pramod\",\n" +
                "        \"lastname\": \"Dutta\",\n" +
                "        \"totalprice\": 3000,\n" +
                "        \"depositpaid\": true,\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2025-07-22\",\n" +
                "            \"checkout\": \"2025-07-27\"\n" +
                "        },\n" +
                "        \"additionalneeds\": \"Breakfast\"\n" +
                "    }";

        // Setting up request specification
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");

        // header information
        requestSpecification.contentType(ContentType.JSON);

        // Adding payload to body
        requestSpecification.body(request_payload).log().all();

        // Request
        response = requestSpecification.when().log().all().post();

        // Get response to perform validation
        validatableResponse = response.then().log().all();

        // Rest Assured Assertions
        validatableResponse.statusCode(200);

        // Boooking ID != null , firstname == Pramod
        validatableResponse.body("bookingid", Matchers.notNullValue());
        validatableResponse.body("booking.firstname", Matchers.equalTo("pramod"));
        validatableResponse.body("booking.lastname", Matchers.equalTo("Dutta"));
        validatableResponse.body("booking.depositpaid", Matchers.equalTo(true));
        validatableResponse.body("booking.bookingdates.checkin", Matchers.equalTo("2025-07-22"));
        validatableResponse.body("booking.bookingdates.checkout", Matchers.equalTo("2025-07-27"));

        // Response header assertion
        validatableResponse.header("Content-Type", Matchers.containsString("application/json"));

//        // Response time assertion
//        validatableResponse.time(Matchers.lessThan(2000L)); // response time under 2 seconds

        // Extract the booking ID
        bookingID = response.jsonPath().getInt("bookingid");
        System.out.println("Booking ID: " + bookingID);

        // System.out.println(response.asString()); // optional: raw response output
    }


}
