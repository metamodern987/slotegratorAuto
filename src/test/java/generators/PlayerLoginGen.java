package generators;

import pojos.PlayerLoginRequest;

/**
 * @author gosha
 * @created 08.05.2021
 */

public class PlayerLoginGen {

    public static PlayerLoginRequest createPlayerLoginReq(String username, String password){
        PlayerLoginRequest loginPl = new PlayerLoginRequest();
        loginPl.setGrant_type("password");
        loginPl.setUsername(username);
        loginPl.setPassword(password);
        return loginPl;

    }
}
