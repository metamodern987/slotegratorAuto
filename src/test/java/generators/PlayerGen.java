package generators;

import pojos.PlayerRequest;

import java.util.Random;

/**
 * @author gosha
 * @created 08.05.2021
 */

public class PlayerGen {

    public static PlayerRequest getNewPlayer(){
        PlayerRequest newPlayerRequest = new PlayerRequest();
        newPlayerRequest.setUsername("autouser" + String.valueOf(Long.valueOf(new Random().nextInt(90)+10)));
        newPlayerRequest.setPassword_change("amFuZWRvZTEyMw==");
        newPlayerRequest.setPassword_repeat("amFuZWRvZTEyMw==");
        newPlayerRequest.setEmail(newPlayerRequest.getUsername() + "@example.com");
        newPlayerRequest.setName("Auto");
        newPlayerRequest.setSurname("User");
        newPlayerRequest.setCurrency_code("RUB");
        return newPlayerRequest;
    }
}
