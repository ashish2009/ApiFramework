package com.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.Iterator;

public class Example {

    public static void update(JsonNode root,String key,String value){
        if(root.has(key)){
            ((ObjectNode) root).put(key, value);
        }

        Iterator<JsonNode>  nodes = root.iterator();

        while (nodes.hasNext()){
            JsonNode next = nodes.next();
            if(next.isContainerNode()){

                if(next.has(key)){
                    ((ObjectNode) next).put(key, value);

                }else {
                    update(next,key,value);
                }
            }

        }

    }

    public static void update(JsonNode root,String key,String hasValue,String value){

        if(!root.isArray()){
            ObjectNode rootNode = ((ObjectNode) root);
            String nodeV = rootNode.findValue(key).textValue();
            if(root.has(key) && nodeV.equalsIgnoreCase(hasValue)){
                rootNode.put(key, value);
            }
        }

        Iterator<JsonNode> nodes = root.iterator();
        while (nodes.hasNext()){
            JsonNode next = nodes.next();
            if(next.isContainerNode()){
                if(next.has(key)){
                    ObjectNode objectNode = ((ObjectNode) next);
                    String s = objectNode.findValue(key).textValue();
                    if(s.equalsIgnoreCase(hasValue))
                    ((ObjectNode) next).put(key, value);

                }else {
                    update(next,key,hasValue,value);
                }
            }

        }

    }

    public static void main(String[] args){
        String json = "{\"id\":\"0001\",\"type\":\"donut\",\"name\":\"Cake\",\"ppu\":0.55,\"batters\":{\"batter\":[{\"id\":\"1001\",\"type\":\"Regular\"},{\"id\":\"1002\",\"type\":\"Chocolate\"},{\"id\":\"1003\",\"type\":\"Blueberry\"},{\"id\":\"1004\",\"type\":\"Devil's Food\"}]},\"topping\":[{\"id\":\"5001\",\"type\":\"None\"},{\"id\":\"5002\",\"type\":\"Glazed\"},{\"id\":\"5005\",\"type\":\"Sugar\"},{\"id\":\"5007\",\"type\":\"Powdered Sugar\"},{\"id\":\"5006\",\"type\":\"Chocolate with Sprinkles\"},{\"id\":\"5003\",\"type\":\"Chocolate\"},{\"id\":\"5004\",\"type\":\"Maple\"}]}";

        ObjectMapper mapper = new ObjectMapper();
//        try {
//            HashMap map = mapper.readValue(json, HashMap.class);
//            for(Object o:map.keySet()){
//                System.out.println("key: "+o.toString());
//                System.out.println("value: "+map.get(o).toString());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
//            System.out.println(map);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String key = "id";
        String value = "0";
        try {
            JsonNode root = mapper.readTree(json);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root));

            update(root,key,"5001",value);
//            update(root,key,value);




//            Iterator<Map.Entry<String, JsonNode>> fields = root.fields();
//
//            while (fields.hasNext()){
//                if(fields.next().getValue().has(key)){
//                    ((ObjectNode) root).put(key, value);
//                }else {
//                    if(fields.next().getValue().isContainerNode()){
//                        JsonNode node = fields.next().getValue();
//                        Iterator<Map.Entry<String, JsonNode>> childs = node.fields();
//                        while (childs.hasNext()){
//                            if(childs.next().getValue().has(key)){
//                                ((ObjectNode) node).put(key, value);
//                            }
//                        }
//                    }
//                }
//
//            }

            System.out.println("After update======================================");
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
