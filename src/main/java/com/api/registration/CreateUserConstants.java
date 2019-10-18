package com.api.registration;

import com.api.Constants;

public class CreateUserConstants extends Constants {

    public static final String header = acceptHeader+","+contentTypeHeader+","+authHeader;
    public static final String path = "/public-api/users";
    public static final String body = "/registration/CreateUser.json";


    public static final String getBody(String email){
        String apiBody = createBody(body);
        return getFormatter().format(apiBody,email).toString();
    }
}
