package com.neuSpring18.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealerIO {
    /**
     *
     * @return a collection of Dealers' ID
     */
    public static List<String> getAllDealers() {
        String file="C:\\Users\\YueTa\\IdeaProjects\\18-Spring-Final-project\\data\\dealers";
        List<String> result = new ArrayList<>();
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(file));
            String read;
            while((read=br.readLine())!=null){
                String[] splited=read.split("\t");
                if(splited.length>0) {
                    result.add(splited[0]);
                }
            }
        }catch(Exception e){
            System.out.println("There was a problem: "+e);
            e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
