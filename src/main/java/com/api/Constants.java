package com.api;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Formatter;

public class Constants {

    public static final String acceptHeader="Accept:application/json";
    public static final String contentTypeHeader="Content-Type:application/json";
    public static final String authHeader="Authorization:Bearer 4L4tavtlVqUK5CU2XDhmsjOuJZHm7VKvkXy4";
    public static final String quikrHeader = "X-Quikr-Client:desktopsite";

    public static String createBody(String file){
        FileReader reader = null;
        String f = System.getProperty("user.dir")+"/src/main/java/com/api"+file;
        try {
            reader = new FileReader(f);
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode object = objectMapper.readValue(reader,ObjectNode.class);
            return objectMapper.writeValueAsString(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Formatter getFormatter(){
        return new Formatter();
    }
}
