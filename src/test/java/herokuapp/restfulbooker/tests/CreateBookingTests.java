package herokuapp.restfulbooker.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.notNullValue;

public class CreateBookingTests {

    @Test(enabled = true) //Create booking with all required fields
    public void CreateBooking_with_all_data() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        validatableResponse.body("bookingid", notNullValue());

    }
    @Test (enabled = true)//Create booking with all required fields
    public void CreateBooking_with_empty_body() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);

    }
    @Test (enabled = true)//Create booking with missing required fields
    public void CreateBooking_with_invalid_json() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
                "    \"firstname\": \"Jim\",\n" +
                "    \"lastname\": \"Brown\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\",\n" +
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(400);

    }

    @Test (enabled = true)//Create booking with without optional data: "additionalneeds"
    public void CreateBooking_without_optional_data() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
                "    \"firstname\": \"Jim\",\n" +
                "    \"lastname\": \"Brown\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    }\n" +
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

    @Test (enabled = true)//Create booking without required data
    public void CreateBooking_without_required_data() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
                "    \"firstname\": \"Jim\",\n" +    //--> 500 Internal Server error
                "    \"lastname\": \"Brown\",\n" +     //--> 500 Internal Server error
//                "    \"totalprice\": 111,\n" +         //--> 500 Internal Server error
                "    \"depositpaid\": true,\n" +       //--> 500 Internal Server error
                "    \"bookingdates\": {\n" +          //--> 400 Internal Server error
                "        \"checkin\": \"2018-01-01\",\n" +  //--> 500 Internal Server error
                "        \"checkout\": \"2019-01-01\"\n" +  //--> 400 bad request
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +  //--> 200 optional data
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);

    }

    @Test(enabled = true) //Create booking with invalid value: invalid date
    public void CreateBooking_with_invalid_field_name() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
                "    \"firstnamefirstname\" : \"Jim\",\n" + //invalid field name
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);


    }
    @Test(enabled = true) //Create booking with invalid value: invalid date
    public void CreateBooking_with_invalid_field_value() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
//                "    \"firstname\" : \"Jim\",\n" +
                "    \"firstname\" : false,\n" +   //-->Bug/issue--> firstname is string, but boolean(false only) is
                // accepted(bug) and returns booking name as false(200).
                //true or 111 -> 500 internal server error// 000 - 400 bad request
                "    \"lastname\" : \"Brown\",\n" + //--< 500 internal server error if different data type is entered,
                // number/boolean instead of string
                "    \"totalprice\" : 111,\n" +       //--> 400 Bad request if different data type is entered ,
                // string instead of number.
                "    \"depositpaid\" : 311,\n" +      //--> Bug/issue-> depositpaid is boolean but giving number "311" or string
                // "jim" as value always returns true(status also 200).
                "    \"bookingdates\" : {\n" +
//                "        \"checkin\" : \"2018-00-00\",\n" +  //invalid field value --->Bug/issue  if checkin and checkout dates are empty or invalid then
//                                                            booking dates shown as : 0NaN-aN-aN  ,
                "        \"checkin\" : \"\",\n" +  //invalid field value  --> another bug enter 1 gives data as 2001-01-01
//                "        \"checkout\" : \"2019-00-00\"\n" + //invalid field value
                "        \"checkout\" : \"\"\n" + //invalid field value
                "    },\n" +
//                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "    \"additionalneeds\" : 123\n" + //empty values and different data types  are accepted for additonalneeds -->it is optional data
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


    }

    @Test (enabled = true)//Create booking without content type header
    public void CreateBooking_without_contentType() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
//        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(500);

    }


    @Test (enabled = true)//Create booking without optional header - accept:application/json
    public void CreateBooking_without_accept() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
//        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

    @Test (enabled = true)//Create booking with additional data(not valid)
    public void CreateBooking_edge_case_additional_data() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"middlename\" : \"rown\",\n" +  //-->Invalid field
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"individualprice\" : 21,\n" +    //-->Invalid field
                "    \"depositpaid\" : true,\n" +
                "    \"donationpaid\" : false,\n" +     //-->Invalid field
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
//        validatableResponse.statusCode(422); //HTTP 422 Unprocessable Entity (it enforce strict schema validation)
        validatableResponse.statusCode(200); //it doesn't enforce strict schema validation


    }

    @Test (enabled = true)//Create booking with additional data(not valid)
    public void CreateBooking_edge_case_boundary_data() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
                "    \"firstname\" : \"JimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJimJim\",\n" +
                "    \"lastname\" : \"BrownBrownBrownBrownBrownBrownBrownBrownBrownBrownBrownBrownBrownBrownBrownBrownBrownBrownBrown\",\n" +
                "    \"totalprice\" : 1231231231231231231231,\n" +// for  123123123123123123(last digit truncated
                // in output: 123123123123123120), for 123123123123123123123   output is truncated and  2 is missing 123123123123123130000
                // but working fine until 12312312312312312(17 digits)
                // for 1231231231231231231231, out put is 1.
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"BreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfastBreakfast\"\n" +
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
//        validatableResponse.statusCode(422); //HTTP 422 Unprocessable Entity (it enforce strict schema validation)
        validatableResponse.statusCode(200); //it doesn't enforce strict schema validation


    }

    @Test (enabled = true)//Create booking with special Characters as firstname, lastname, additionalneeds like in
    // RTL(arabic),DBCS(korean,japanese) and ASCII(English) style.
    public void CreateBooking_edge_case_special_characters() {
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType("application/json");
        requestSpecification.accept("application/json");
        requestSpecification.body("{\n" +
//                "    \"firstname\" : \"ᴊͥɪͣᴍͫ\",\n" +
                "    \"firstname\" : \"نام <\",\n" +
                "    \"lastname\" : \"ʙͥʀᴏᴡͣɴͫ\",\n" +
                "    \"totalprice\" : 121,\n" +// for  123123123123123123(last digit truncated
                // in output: 123123123123123120), for 123123123123123123123   output is truncated and  2 is missing 123123123123123130000
                // but working fine until 12312312312312312(17 digits)
                // for 1231231231231231231231, out put is 1.
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-01-01\",\n" +
                "        \"checkout\" : \"2025-01-05\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"이름(꽃)\"\n" +
                "}");

        Response response = requestSpecification.when().post();
        ValidatableResponse validatableResponse = response.then().log().all();
//        validatableResponse.statusCode(422); //HTTP 422 Unprocessable Entity (it enforce strict schema validation)
        validatableResponse.statusCode(200); //it doesn't enforce strict schema validation


    }
}
