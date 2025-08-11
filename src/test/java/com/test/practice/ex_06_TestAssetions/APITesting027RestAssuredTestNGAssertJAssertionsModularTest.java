package com.test.practice.ex_06_TestAssetions;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting027RestAssuredTestNGAssertJAssertionsModularTest {

    RequestSpecification requestSpecification;
    ValidatableResponse validatableResponse;
    Response response;
    Integer bookingId;

    private String firstname, lastname, checkin, checkout, additionalneeds;
    private int totalprice;
    private boolean depositpaid;

    @Test
    public void test_POST() {
        String payload = createPayload();
        setupRequest(payload);
        executePostRequest();
        performDefaultRestAssuredAssertions();
        performHamcrestAssertions(); //
        extractResponseData();
        performAssertJValidations();
        performTestNGValidations();
    }

    private String createPayload() {
        return "{\n" +
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
    }

    private void setupRequest(String payload) {
        requestSpecification = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .body(payload);
    }

    private void executePostRequest() {
        response = requestSpecification.when().post();
        validatableResponse = response.then();
    }

    private void performDefaultRestAssuredAssertions() {
        validatableResponse
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
    }

    private void performHamcrestAssertions() {
        validatableResponse.body("bookingid", notNullValue());
        validatableResponse.body("booking.firstname", equalTo("Pramod"));
        validatableResponse.body("booking.lastname", equalTo("Dutta"));
        validatableResponse.body("booking.totalprice", equalTo(123));
        validatableResponse.body("booking.depositpaid", equalTo(false));
        validatableResponse.body("booking.bookingdates.checkin", equalTo("2024-01-01"));
        validatableResponse.body("booking.bookingdates.checkout", equalTo("2024-01-01"));
        validatableResponse.body("booking.additionalneeds", equalTo("Lunch"));
    }

    private void extractResponseData() {
        bookingId = response.then().extract().path("bookingid");
        firstname = response.then().extract().path("booking.firstname");
        lastname = response.then().extract().path("booking.lastname");
        totalprice = response.then().extract().path("booking.totalprice");
        depositpaid = response.then().extract().path("booking.depositpaid");
        checkin = response.then().extract().path("booking.bookingdates.checkin");
        checkout = response.then().extract().path("booking.bookingdates.checkout");
        additionalneeds = response.then().extract().path("booking.additionalneeds");
    }

    private void performAssertJValidations() {
        assertThat(bookingId).isNotNull().isPositive();
        assertThat(firstname).isEqualTo("Pramod").isNotBlank();
        assertThat(lastname).isEqualTo("Dutta");
        assertThat(totalprice).isEqualTo(123);
        assertThat(depositpaid).isFalse();
        assertThat(checkin).isEqualTo("2024-01-01");
        assertThat(checkout).isEqualTo("2024-01-01");
        assertThat(additionalneeds).isEqualTo("Lunch");
    }

    private void performTestNGValidations() {
        Assert.assertNotNull(bookingId, "Booking ID should not be null");
        Assert.assertEquals(firstname, "Pramod", "Firstname mismatch");
        Assert.assertEquals(lastname, "Dutta", "Lastname mismatch");
        Assert.assertEquals(totalprice, 123, "Total price mismatch");
        Assert.assertFalse(depositpaid, "DepositPaid should be false");
        Assert.assertEquals(checkin, "2024-01-01", "Check-in date mismatch");
        Assert.assertEquals(checkout, "2024-01-01", "Check-out date mismatch");
        Assert.assertEquals(additionalneeds, "Lunch", "Additional needs mismatch");

        if (!firstname.contains("Pramod")) {
            Assert.fail("Firstname does not contain expected value");
        }
    }
}