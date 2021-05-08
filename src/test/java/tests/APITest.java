package tests;

import generators.CredentialsGen;
import generators.PlayerGen;
import generators.PlayerLoginGen;
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


    @Test
    public void getTokenGuest(){

        Credentials creds = CredentialsGen.getCreds();
        Token token = RestWrapper.getTokenGuest(creds);

        Assert.assertTrue(token.getAccess_token() != null);

    }

    @Test
    public void registerPlayer(){

        PlayerRequest newPlayerRequest = PlayerGen.getNewPlayer();
        Credentials creds = CredentialsGen.getCreds();
        Token token = RestWrapper.getTokenGuest(creds);
        String bearerToken = token.getAccess_token();

        PlayerProfile newPlayer = RestWrapper.registerNewPlayer(newPlayerRequest, bearerToken);

        Assert.assertTrue(newPlayer.getUsername().contains(newPlayerRequest.getUsername()));
    }

    @Test
    public void loginPlayer(){
        PlayerRequest newPlayerRequest = PlayerGen.getNewPlayer();
        Credentials creds = CredentialsGen.getCreds();
        Token token = RestWrapper.getTokenGuest(creds);
        String bearerToken = token.getAccess_token();

        PlayerProfile newPlayer = RestWrapper.registerNewPlayer(newPlayerRequest, bearerToken);

        PlayerLoginRequest loginPl = PlayerLoginGen.createPlayerLoginReq(newPlayer.getUsername(), newPlayerRequest.getPassword_change());

        Token tokenPlayer = RestWrapper.getTokenPlayer(loginPl);

        Assert.assertTrue(tokenPlayer.getAccess_token() != null);

    }

    @Test
    public void getPlayerById(){
        PlayerRequest newPlayerRequest = PlayerGen.getNewPlayer();
        Credentials creds = CredentialsGen.getCreds();
        Token token = RestWrapper.getTokenGuest(creds);
        String bearerToken = token.getAccess_token();

        PlayerProfile newPlayer = RestWrapper.registerNewPlayer(newPlayerRequest, bearerToken);

        PlayerLoginRequest loginPl = PlayerLoginGen.createPlayerLoginReq(newPlayer.getUsername(), newPlayerRequest.getPassword_change());

        Token tokenPlayer = RestWrapper.getTokenPlayer(loginPl);
        PlayerProfile[] currentPlayer = RestWrapper.getProtectedPlayer(tokenPlayer.getAccess_token());


    }

    @Test
    public void getAnotherPlayer(){
        PlayerRequest newPlayerRequest = PlayerGen.getNewPlayer();
        Credentials creds = CredentialsGen.getCreds();
        Token token = RestWrapper.getTokenGuest(creds);
        String bearerToken = token.getAccess_token();

        PlayerProfile newPlayer = RestWrapper.registerNewPlayer(newPlayerRequest, bearerToken);

        PlayerLoginRequest loginPl = PlayerLoginGen.createPlayerLoginReq(newPlayer.getUsername(), newPlayerRequest.getPassword_change());

        Token tokenPlayer = RestWrapper.getTokenPlayer(loginPl);
        Response resp = RestWrapper.getAnotherPlayerById(tokenPlayer.getAccess_token());
    }
}
