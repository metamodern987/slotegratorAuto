package services;

import io.restassured.http.Cookies;
import rest.RestEntityService;

/**
 * Created by igor on 06.05.2021
 */

public class PlayerService extends RestEntityService<PlayerService> {

    public PlayerService(Cookies cookies){
        super(cookies);
    }
}
