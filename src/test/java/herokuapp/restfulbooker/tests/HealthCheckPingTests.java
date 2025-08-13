package herokuapp.restfulbooker.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.UnknownHostException;

import static org.hamcrest.Matchers.*;

//Why a 201 Created for a GET?
//Normally, a GET request should return 200 OK, not 201 Created. A 201 implies that something was created, which is typical for POST requests.
//So this is a bit unconventional. Possible explanations:
//- The server might be using 201 Created as a generic success response for its ping endpoint.
//- It could be a misconfiguration or a custom behavior in the Express.js app.
//A simple health check endpoint to confirm whether the API is up and running.
public class HealthCheckPingTests {

    @Test(enabled = true)
    public void getEndpointStatus_https() {

        //Setup Request
        //https://restful-booker.herokuapp.com/ping
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/ping");

        // Send Request
        Response response = requestSpecification.when().log().all().get();

        //Extract response
        int statusCode = response.statusCode();
        System.out.println("\nResponse: StatusCode: " + statusCode);
        String contentType = response.contentType(); // we can also use response.getContentType(), both are same.
        System.out.println("Response: ContentType: " + contentType);
        String responseBody = response.getBody().asString();
        System.out.println("Response: responseBody: " + responseBody + "\n");

        // Validate Response
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
        validatableResponse.contentType("text/plain; charset=utf-8");
        validatableResponse.body(equalTo("Created"));
        validatableResponse.body(notNullValue());

        // Optional: Add assertions
        assert statusCode == 201 : "Expected status 201";
        assert contentType.contains("text/plain") : "Expected text/plain content type";
        assert responseBody.equals("Created") : "Expected body to be 'Created'";

        // Optional: TestNg Assertions
        Assert.assertEquals(statusCode, 201, "Expected status 201");
        Assert.assertEquals(contentType, "text/plain; charset=utf-8", "Expected text/plain content type");
        Assert.assertEquals(responseBody, "Created", "Expected body to be 'Created'");

    }


    @Test(enabled = true)
    public void getEndpointStatus_http() {

        //Setup Request
        //http://restful-booker.herokuapp.com/ping
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("http://restful-booker.herokuapp.com");
        requestSpecification.basePath("/ping");

        // Send Request
        Response response = requestSpecification.when().log().all().get();

        //Extract response
        int statusCode = response.statusCode();
        System.out.println("\nResponse: StatusCode: " + statusCode);
        String contentType = response.contentType(); // we can also use response.getContentType(), both are same.
        System.out.println("Response: ContentType: " + contentType);
        String responseBody = response.getBody().asString();
        System.out.println("Response: responseBody: " + responseBody + "\n");

        // Validate Response
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);
        validatableResponse.contentType("text/plain; charset=utf-8");
        validatableResponse.body(equalTo("Created"));
        validatableResponse.body(notNullValue());

        // Optional: Add assertions
        assert statusCode == 201 : "Expected status 201";
        assert contentType.contains("text/plain") : "Expected text/plain content type";
        assert responseBody.equals("Created") : "Expected body to be 'Created'";

        // Optional: TestNg Assertions
        Assert.assertEquals(statusCode, 201, "Expected status 201");
        Assert.assertEquals(contentType, "text/plain; charset=utf-8", "Expected text/plain content type");
        Assert.assertEquals(responseBody, "Created", "Expected body to be 'Created'");


    }


    @Test(enabled = true)
    public void getEndpointStatus_Invalid_Uri_Path() {

        //Setup Request
        //https://restful-booker.herokuapp.com/pings - invalid uri path
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/pings");

        // Send Request
        Response response = requestSpecification.when().log().all().get();

        //Extract response
        int statusCode = response.statusCode();
        System.out.println("\nResponse: StatusCode: " + statusCode);
        String contentType = response.contentType(); // we can also use response.getContentType(), both are same.
        System.out.println("Response: ContentType: " + contentType);
        String responseBody = response.getBody().asString();
        System.out.println("Response: responseBody: " + responseBody + "\n");

        // Validate Response
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
        validatableResponse.contentType("text/plain; charset=utf-8");
        validatableResponse.body(equalTo("Not Found"));
        validatableResponse.body(notNullValue());

        // Optional: Add assertions
        assert statusCode == 404 : "Expected status 201";
        assert contentType.contains("text/plain") : "Expected text/plain content type";
        assert responseBody.equals("Not Found") : "Expected body to be 'Not Found'";

        // Optional: TestNg Assertions
        Assert.assertEquals(statusCode, 404, "Expected status 201");
        Assert.assertEquals(contentType, "text/plain; charset=utf-8", "Expected text/plain content type");
        Assert.assertEquals(responseBody, "Not Found", "Expected body to be 'Not Found'");


    }

    @Test(enabled = true)
    public void getEndpointStatus_Invalid_Base_Uri_subdomain() {

        //Setup Request
        //https://restful_booker.herokuapp.com/ping - invalid base uri (instead of restful-booker, used restful_booker.
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful_booker.herokuapp.com");
        requestSpecification.basePath("/ping");

        // Send Request
        Response response = requestSpecification.when().log().all().get();

        //Extract response
        int statusCode = response.statusCode();
        System.out.println("\nResponse: StatusCode: " + statusCode);
        String contentType = response.contentType(); // we can also use response.getContentType(), both are same.
        System.out.println("Response: ContentType: " + contentType + "\n");

        // Validate Response
        ValidatableResponse validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);
        validatableResponse.contentType("text/html; charset=utf-8");
        validatableResponse.body(notNullValue());

        // Optional: Add assertions
        assert statusCode == 404 : "Expected status 201";
        assert contentType.contains("text/html; charset=utf-8") : "Expected text/html; charset=utf-8 content type";

        // Optional: TestNg Assertions
        Assert.assertEquals(statusCode, 404, "Expected status 201");
        Assert.assertEquals(contentType, "text/html; charset=utf-8", "Expected text/html; charset=utf-8 content type");


    }

    @Test(enabled = true)
    public void getEndpointStatus_Invalid_Base_Uri_Domain() {
        try {
            // Setup Request
            RequestSpecification requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.aherokuapp.com"); // intentionally wrong
            requestSpecification.basePath("/ping");

            // Send Request — this should fail due to UnknownHostException
            Response response = requestSpecification.when().log().all().get();

            // If we reach here, then no exception occurred → test fails
            Assert.fail("Expected UnknownHostException, but request succeeded with status: "
                    + response.statusCode());

        } catch (Exception e) {
            if (containsUnknownHostException(e)) {
                //  Expected scenario — exception occurred, pass the test
                System.out.println("Test passed: UnknownHostException occurred as expected.");
            } else {
                //  Some other unexpected exception — fail the test
                Assert.fail("Test failed due to unexpected exception: " + e.getMessage(), e);
            }
        }
    }


    private boolean containsUnknownHostException(Throwable e) {
        while (e != null) {
            if (e instanceof UnknownHostException) {
                return true;
            }
            e = e.getCause();
        }
        return false;
    }


}
