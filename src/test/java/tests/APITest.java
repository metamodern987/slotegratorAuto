package tests;

import generators.CredentialsGen;
import io.restassured.http.ContentType;
import org.junit.Assert;
import org.junit.Test;
import pojos.*;
import rest.RestWrapper;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;

/**
 * Created by igor on 06.05.2021
 */

public class APITest {

    protected static final String AUTH_USERNAME = "front_2d6b0a8391742f5d789d7d915755e09e";
    protected static final String BASE_URL = "http://test-api.d6.dev.devcaz.com";
    protected static final String AUTH_URL = BASE_URL + "/v2/oauth2/token";
    protected static final String REGISTER_URL = BASE_URL + "/v2/players";

    @Test
    public void getTokenGuest(){

        Credentials creds = CredentialsGen.getCreds();

        Token token = RestWrapper.getTokenGuest(creds);

        Assert.assertTrue(token.getAccess_token() != null);


    }

    @Test
    public void registerPlayer(){

        PlayerRequest newPlayerRequest = new PlayerRequest();
        newPlayerRequest.setUsername("autouser1");
        newPlayerRequest.setPassword_change("amFuZWRvZTEyMw==");
        newPlayerRequest.setPassword_repeat("amFuZWRvZTEyMw==");
        newPlayerRequest.setEmail("autouser1@example.com");
        newPlayerRequest.setName("Auto");
        newPlayerRequest.setSurname("User");
        newPlayerRequest.setCurrency_code("RUB");

        Credentials creds = CredentialsGen.getCreds();
        Token token = RestWrapper.getTokenGuest(creds);

        String bearerToken = token.getAccess_token();

        PlayerProfile newPlayer = given().headers(
                "Authorization",
                "Bearer " + bearerToken)
                                        .contentType(ContentType.JSON)
                                        .body(newPlayerRequest)

                                .expect()
                                    .statusCode(201)
                                .when()
                                     .post(REGISTER_URL)
                                .then()
                                     .log().all().extract().body().as(PlayerProfile.class);

        Assert.assertTrue(newPlayer.getUsername().contains("autouser1"));
    }

    @Test
    public void loginPlayer(){

        PlayerLoginRequest loginPl = new PlayerLoginRequest();
        loginPl.setGrant_type("password");
        loginPl.setUsername("autouser");
        loginPl.setPassword("amFuZWRvZTEyMw==");

        Token tokenPlayer = given()
                           //.accept("application/json")
                             .contentType(ContentType.JSON)
                             .body(loginPl)
                             .auth().preemptive().basic(AUTH_USERNAME, "")
                .expect()
                      .statusCode(200)
                .when()
                      .post(AUTH_URL)
                .then()
                      .log().all().extract().body().as(Token.class);

        Assert.assertTrue(tokenPlayer.getAccess_token() != null);

    }

    @Test
    public void getPlayerById(){

    }
}
