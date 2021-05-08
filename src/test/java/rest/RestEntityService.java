package rest;

import io.restassured.http.Cookies;

/**
 * Created by igor on 06.05.2021
 */

public abstract class RestEntityService<TService> {

    protected Cookies cookies;

    public RestEntityService(Cookies cookies){
        this.cookies = cookies;
    }

    protected static final String BASE_URL = "http://test-api.d6.dev.devcaz.com";
}
