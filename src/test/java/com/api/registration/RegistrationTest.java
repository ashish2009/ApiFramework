package com.api.registration;


import com.api.ApiBase;
import com.api.registeredUser.RegisteredUserConstant;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class RegistrationTest {

    //1746,1745

    @Test
    public void verifyCreateUserResp(){
        ApiBase apiBase = new ApiBase("fixedRegistration.FixedUserConstants");
        Response response =apiBase.getRequest().returnPostApiResp();
        System.out.println(response.asString());
    }


    @Test(dataProvider = "registrationEmail")
    public void verifyCreateUser(String email){
        ApiBase apiBase = new ApiBase("registration.CreateUserConstants");
        Object m =apiBase.getRequest().setBody(CreateUserConstants.getBody(email)).returnParamsFromResp("_meta.message");
        System.out.println(String.valueOf(m));
    }

    @Test
    public void verifyUserCreated(){
        ApiBase apiBase = new ApiBase("fixedRegisteredUser.FixedRegisteredUserConstant");
        Response response = apiBase.getRequest().returnGetApiResp();
        System.out.println(response.asString());
    }

    @Test(dataProvider = "userId")
    public void verifyUserCreated(String userId){
        ApiBase apiBase = new ApiBase("registeredUser.RegisteredUserConstant");
        Response response = apiBase.getRequest().setPathParam(RegisteredUserConstant.getPathParameter(userId)).returnGetApiResp();
        System.out.println(response.asString());
    }

    @DataProvider(name = "registrationEmail")
    public static Object[][] emailData() {

        return new Object[][]{
                {"ashish6@gmail.com"}
        };
    }

    @DataProvider(name = "userId")
    public static Object[][] userIdData() {

        return new Object[][]{
                {"1745"}
        };
    }

}
