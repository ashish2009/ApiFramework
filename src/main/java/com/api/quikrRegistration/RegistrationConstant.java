package com.api.quikrRegistration;


import com.api.Constants;

public class RegistrationConstant extends Constants {

    public static final String header = contentTypeHeader+","+quikrHeader;
    public static final String path = "/platform/v1/registerUser";
    public static final String body = "/quikrRegistration/register.json";
}
