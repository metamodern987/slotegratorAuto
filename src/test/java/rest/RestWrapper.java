package rest;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.ResponseSpecification;
import pojos.Credentials;
import pojos.Token;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by igor on 07.05.2021
 */

public class RestWrapper {

    protected static final String BASE_URL = "http://test-api.d6.dev.devcaz.com";
    protected static final String AUTH_URL = BASE_URL + "/v2/oauth2/token";
    protected static final String AUTH_USERNAME = "front_2d6b0a8391742f5d789d7d915755e09e";

    protected static final ResponseSpecification respSpec = new ResponseSpecBuilder()
            .expectStatusCode(200).build();

    private Cookies cookies;

    private RestWrapper(Cookies cookies){
        this.cookies=cookies;
    }

    public static Token getTokenGuest(Credentials creds) {
        Token token = given().contentType(ContentType.JSON)
                                 .body(creds)
                                 .auth().preemptive().basic(AUTH_USERNAME, "")
                .expect().spec(respSpec)
                .when().post(AUTH_URL)
                .then()
                   .log().all().extract().body().as(Token.class);
        return token;
    }
}
