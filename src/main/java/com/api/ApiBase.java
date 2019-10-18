package com.api;

import com.api.utils.PropertyReader;

import java.lang.reflect.Field;
import java.util.HashMap;

public class ApiBase {

    Field header;
    Field query;
    Field path;
    Field body;
    Field pathParameter;
    HashMap<String,String> prop = PropertyReader.getProperties();
    String className;
    RequestHandler requestHandler;

    public ApiBase(String className){
        this.className = "com.api."+className;
        requestHandler = new RequestHandler();
    }

    private String getUrl(){
        String env = prop.get("env");
        if(env.equalsIgnoreCase("stage")){
            return prop.get("stageUrl");
        }else {
            return prop.get("prodUrl");
        }
    }

    private Class getRequestClass(){
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isPathPresent(){
        try {
            path = getRequestClass().getDeclaredField("path");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private boolean isHeaderPresent(){
        try {
            header = getRequestClass().getDeclaredField("header");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private boolean isQueryPresent(){
        try {
            query = getRequestClass().getDeclaredField("query");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private boolean isBodyPresent(){
        try {
            body = getRequestClass().getDeclaredField("body");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private boolean isPathParamPresent(){
        try {
            pathParameter = getRequestClass().getDeclaredField("pathParameter");
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    private boolean isBodyParameterised(){
        try {
            getRequestClass().getDeclaredMethod("getBody");
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private boolean isQueryParameterised(){
        try {
            getRequestClass().getDeclaredMethod("getQuery");
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private boolean isPathParameterised(){
        try {
            getRequestClass().getDeclaredMethod("getPathParameter");
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }



    public RequestHandler getRequest(){
        requestHandler.setEndPoint(getUrl());
        try {
            if(isPathPresent())
                requestHandler.setBasePath(path.get(getRequestClass()).toString());

            if(isHeaderPresent())
                requestHandler.setHeader(header.get(getRequestClass()).toString());

            if(isQueryPresent() && !isQueryParameterised())
                requestHandler.setQuery(query.get(getRequestClass()).toString());

            if(isBodyPresent() && !isBodyParameterised())
                requestHandler.setBody(Constants.createBody(body.get(getRequestClass()).toString()));

            if(isPathParamPresent() && !isPathParameterised())
                requestHandler.setPathParam(pathParameter.get(getRequestClass()).toString());


        }catch (Exception e){
            e.printStackTrace();
        }
        return requestHandler;
    }




}
