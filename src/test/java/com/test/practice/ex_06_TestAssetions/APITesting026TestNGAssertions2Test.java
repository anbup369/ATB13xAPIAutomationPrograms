package com.test.practice.ex_06_TestAssetions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting026TestNGAssertions2Test {

    @Test
    public void test_createBooking_POST() {

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

        // Setup request
        RequestSpecification requestSpecification = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .body(request_payload)
                .log().all();

        // Send POST request
        Response response = requestSpecification.when().post();

        // Log response
        response.then().log().all();

        //  TestNG Assertions
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");

        Integer bookingID = response.jsonPath().getInt("bookingid");
        Assert.assertNotNull(bookingID, "Booking ID should not be null");

        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), "pramod", "Firstname mismatch");
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), "Dutta", "Lastname mismatch");
        Assert.assertTrue(response.jsonPath().getBoolean("booking.depositpaid"), "DepositPaid should be true");

        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkin"), "2025-07-22", "Check-in date mismatch");
        Assert.assertEquals(response.jsonPath().getString("booking.bookingdates.checkout"), "2025-07-27", "Check-out date mismatch");

        String contentType = response.getHeader("Content-Type");
        Assert.assertTrue(contentType.contains("application/json"), "Content-Type should contain application/json");

//        // Optional: Response time assertion
//        Assert.assertTrue(response.getTime() < 2000, "Response time should be under 2 seconds");

        System.out.println("Booking ID: " + bookingID);
    }
}
