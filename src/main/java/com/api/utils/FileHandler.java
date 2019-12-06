package com.api.utils;

import java.io.*;
import java.util.stream.Stream;

public class FileHandler {
//    ffisisrbr ffwbw

    public static Stream<String> read(String path) throws FileNotFoundException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        InputStreamReader  isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        return bufferedReader.lines();
    }

    public static void write(String path,Stream stream) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        stream.forEach((v)-> {
            try{
                bw.write(String.valueOf(v));
            }catch (IOException io){
                io.printStackTrace();
            }

        });

        bw.flush();
    }

    public static void writeAfterFiltering(String path,Stream stream,String filter) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        stream.filter(val->String.valueOf(val).contains(filter)).forEach((v)-> {
            try{
                bw.write(String.valueOf(v));
                bw.write("\n");
            }catch (IOException io){
                io.printStackTrace();
            }

        });

        bw.flush();
    }

    public static void main(String[] args) {
        try {
            Stream<String> r = read("/Users/ashishkumar/Desktop/ApiTestFramework/src/main/java/com/api/utils/example");
            writeAfterFiltering("/Users/ashishkumar/Desktop/ApiTestFramework/src/main/java/com/api/utils/exampleOut",r,"Quikr");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
