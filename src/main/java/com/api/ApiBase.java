package com.api;

import com.api.utils.PropertyReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /*
       This method will fetch value of key from Json
     */
    public String getValueFromJson(String json,String key){
        JsonNode jsonNode = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonNode = objectMapper.readTree(json);
            return jsonNode.findValue(key).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
      This method will fetch list of value for key
     */
    public List<String> getValuesFromJson(String json, String key){
        JsonNode jsonNode = null;
        List<String> ls = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonNode = objectMapper.readTree(json);
            List<JsonNode> nodes =  jsonNode.findValues(key);
            for (JsonNode node:nodes){
                ls.add(node.textValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ls;
    }

    /*
      This method will convert Json to Map
     */
    public HashMap jsonToMapConverter(String json){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json,HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String,Object> jsonToMap(String json){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
      This will map json to object
     */
    public void mapJsonToObject(){

    }

    /*
     This will convert object to json
     */
    public void convertObjectToJson(){

    }

    /*
     This will update value of key in Json
     */
    public void updateJson(String file,String k,Object v){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(new File(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
