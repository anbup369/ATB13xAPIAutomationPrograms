package com.test.practice.ex_06_TestAssetions;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting027RestAssuredTestNGAssertJAssertionsTest {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    Integer bookingId;

    @Test
    public void test_POST() {

        // Payload
        String payload_POST = "{\n" +
                "    \"firstname\" : \"Pramod\",\n" +
                "    \"lastname\" : \"Dutta\",\n" +
                "    \"totalprice\" : 123,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        // Request setup
        requestSpecification = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .body(payload_POST);

        // Execute POST
        response = requestSpecification.when().post();

        // Default RestAssured assertions
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("bookingid", notNullValue())
                .body("booking.firstname", equalTo("Pramod"))
                .body("booking.lastname", equalTo("Dutta"))
                .body("booking.totalprice", equalTo(123))
                .body("booking.depositpaid", equalTo(false))
                .body("booking.bookingdates.checkin", equalTo("2024-01-01"))
                .body("booking.bookingdates.checkout", equalTo("2024-01-01"))
                .body("booking.additionalneeds", equalTo("Lunch"));

        // Validate response
        // Get response to perform validation
        validatableResponse = response.then();
        validatableResponse.statusCode(200);

        // Hamcrest Assertions (RestAssured built-in)
        // Rest Assured -> import org.hamcrest.Matchers; 5% Usage
        // Matchers.equalto()
        validatableResponse.body("bookingid", notNullValue());
        validatableResponse.body("booking.firstname", Matchers.equalTo("Pramod"));
        validatableResponse.body("booking.lastname", Matchers.equalTo("Dutta"));
        validatableResponse.body("booking.totalprice", Matchers.equalTo(123));
        validatableResponse.body("booking.depositpaid", Matchers.equalTo(false));
        validatableResponse.body("booking.bookingdates.checkin", Matchers.equalTo("2024-01-01"));
        validatableResponse.body("booking.bookingdates.checkout", Matchers.equalTo("2024-01-01"));
        validatableResponse.body("booking.additionalneeds", Matchers.equalTo("Lunch"));

        // Extraction - Extract the details of the firstname, bookingId, lastname from Response.
        // Concept #1 - Normal( TestNG or Assertj) IS IMP
        bookingId = response.then().extract().path("bookingid");
        String firstname = response.then().extract().path("booking.firstname");
        String lastname = response.then().extract().path("booking.lastname");
        int totalprice = response.then().extract().path("booking.totalprice");
        boolean depositpaid = response.then().extract().path("booking.depositpaid");
        String checkin = response.then().extract().path("booking.bookingdates.checkin");
        String checkout = response.then().extract().path("booking.bookingdates.checkout");
        String additionalneeds = response.then().extract().path("booking.additionalneeds");

        // JsonPath alternative extraction
        // Concept #2 - (Jsonpath class) Another mechanism to extract the Keys, value by JSON Path
        JsonPath jp = new JsonPath(response.asString());
//         String bookingID1 = jp.getString("bookingid");

        // AssertJ Assertions ( 3rd- Lib to Assertions) - 20% Usage
        assertThat(bookingId).isNotNull().isPositive();
        assertThat(firstname).isEqualTo("Pramod").isNotBlank();
        assertThat(lastname).isEqualTo("Dutta");
        assertThat(totalprice).isEqualTo(123);
        assertThat(depositpaid).isFalse();
        assertThat(checkin).isEqualTo("2024-01-01");
        assertThat(checkout).isEqualTo("2024-01-01");
        assertThat(additionalneeds).isEqualTo("Lunch");

        // TestNG Assertions - 75% usage
        // SoftAssert vs HardAssert (90%)
        // This means that if any assertion fails,
        // the remaining statements in that test method will not be executed.
        Assert.assertNotNull(bookingId, "Booking ID should not be null");
        Assert.assertEquals(firstname, "Pramod", "Firstname mismatch");
        Assert.assertEquals(lastname, "Dutta", "Lastname mismatch");
        Assert.assertEquals(totalprice, 123, "Total price mismatch");
        Assert.assertFalse(depositpaid, "DepositPaid should be false");
        Assert.assertEquals(checkin, "2024-01-01", "Check-in date mismatch");
        Assert.assertEquals(checkout, "2024-01-01", "Check-out date mismatch");
        Assert.assertEquals(additionalneeds, "Lunch", "Additional needs mismatch");

        // Optional: Custom failure condition
        if (!firstname.contains("Pramod")) {
            Assert.fail("Firstname does not contain expected value");
        }
    }
}