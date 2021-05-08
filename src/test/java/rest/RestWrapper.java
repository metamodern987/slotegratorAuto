package rest;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.ResponseSpecification;
import pojos.*;

import static io.restassured.RestAssured.given;

/**
 * Created by igor on 07.05.2021
 */

public class RestWrapper {

    protected static final String BASE_URL = "http://test-api.d6.dev.devcaz.com";
    protected static final String AUTH_URL = BASE_URL + "/v2/oauth2/token";
    protected static final String AUTH_USERNAME = "front_2d6b0a8391742f5d789d7d915755e09e";
    protected static final String REGISTER_URL = BASE_URL + "/v2/players";

    protected static final ResponseSpecification respSpec = new ResponseSpecBuilder()
            .expectStatusCode(200).build();
    //private static String cookies;

    public Cookies cookies;

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

    public static PlayerProfile registerNewPlayer(PlayerRequest newPlayerReq, String token){
        PlayerProfile newPlayer = given().headers(
                "Authorization",
                "Bearer " + token)
                                         .contentType(ContentType.JSON)
                                         .body(newPlayerReq)

                .expect()
                       .statusCode(201)
                .when()
                       .post(REGISTER_URL)
                .then()
                   .log().all().extract().body().as(PlayerProfile.class);
        return newPlayer;

    }

    public static Token getTokenPlayer(PlayerLoginRequest playerLogReq) {
        Token token = given().contentType(ContentType.JSON)
                              .body(playerLogReq)
                              .auth().preemptive().basic(AUTH_USERNAME, "")
                .expect().spec(respSpec)
                .when().post(AUTH_URL)
                .then()
                .log().all().extract().body().as(Token.class);
        return token;
    }

    public static PlayerProfile[] getProtectedPlayer(String token){
        PlayerProfile player[] = given().headers(
                                                 "Authorization",
                                                 "Bearer " + token)
                                                  .contentType(ContentType.JSON)
                              .expect().spec(respSpec)
                              .when().get(REGISTER_URL)
                              .then()
                                    .log().all().extract().body().as(PlayerProfile[].class);
        return player;

    }

    public static Response getAnotherPlayerById(String token){
        Response resp = given().headers(
                         "Authorization",
                         "Bearer " + token)
                         .contentType(ContentType.JSON)
                .expect().statusCode(404)
                .when().get(REGISTER_URL+"/9999")
                .then()
                       .log().all().extract().body().as(Response.class);
        return  resp;

    }
}
