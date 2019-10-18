package com.api.registeredUser;

import com.api.Constants;

public class RegisteredUserConstant extends Constants {

    public static final String path="/public-api/users/{userId}";
    public static final String pathParameter="userId:%s";
    public static final String header = authHeader;

    public static final String getPathParameter(String userId){
        return String.format(pathParameter,userId);
    }
}
