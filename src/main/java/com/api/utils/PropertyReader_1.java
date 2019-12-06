package com.api.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.io.InputStream;
import java.util.Set;
import java.util.Iterator;

public class PropertyReader_1 {

    public HashMap<String,String> getProperty(){
        String fileName = "";
        HashMap<String,String> map = new HashMap<>();
        Properties properties = new Properties();
        try{
            InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
            properties.load(stream);
            Set<String>  keys = properties.stringPropertyNames();
            Iterator<String> it = keys.iterator();
            while (it.hasNext()){
                String k = it.next();
                map.put(k,properties.getProperty(k));
            }
        }catch(IOException io){

        }
        return map;
    }

}
