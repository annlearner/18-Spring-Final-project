package com.neuSpring18.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserIO implements UserIOInterface {
    private String path=System.getProperty("user.dir")+"\\data\\";

    @Override
    public List<String> getAllBasedOnMode(String mode, String filename) {
        String file=path+filename;
        List<String> result = new ArrayList<>();
        BufferedReader br=null;
        try {
            br = new BufferedReader(new FileReader(file));
            String read;
            while ((read = br.readLine()) != null) {
                if (mode.equals("ID")) {
                    String[] splited = read.split("\t");
                    if (splited.length > 0) {
                        result.add(splited[0]);
                    }
                } else if (mode.equals("All")) {
                    result.add(read);
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

    @Override
    public String addVehicleToDealer(String dealerID, String vehicleString) {
        String selected=path+dealerID;
        String newID=null;
        //hasID no? then generate Id
        while(hasId(selected,newID)) {
            newID = generateId();
        }
        //add the info to file
        FileWriter fw=null;
        BufferedWriter bw=null;
        try {
            fw=new FileWriter(selected,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fw != null) {
            bw=new BufferedWriter(fw);
        }
        if (bw != null) {
            try (PrintWriter pw = new PrintWriter(bw)) {
                pw.println(vehicleString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newID;
    }

    /**
     * Generate a 10 digit ID randomly
     * @return the ID
     */
    private String generateId() {
        long number=(long)Math.floor(Math.random()*9_000_000_000L)+1_000_000_000L;
        return Long.toString(number);
    }

    private boolean hasId(String filename,String ID) {
        List<String> result=getAllBasedOnMode("ID",filename);
        return result.contains(ID);
    }

    @Override
    public boolean editVehicleofDealer(String dealerID, String vehicleID, String vehicleString) {
        String filepath=path+dealerID;
        List<String> lines= new ArrayList<>();
        String line;
        try{
            File f=new File(filepath);
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            while((line=br.readLine())!=null) {
                if (getAllBasedOnMode("ID",dealerID).size() == 0) {
                    return false;
                }
                if (line.contains(vehicleID)) {
                    line = line.replace(vehicleID + ".*?", vehicleID + vehicleString);
                }
                lines.add(line);
            }
            fr.close();
            br.close();

            FileWriter fw=new FileWriter(f);
            BufferedWriter bw=new BufferedWriter(fw);
            for(String s:lines) bw.write(s);
            fw.flush();
            fw.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return true;

    }

    @Override
    public boolean deleteVehiclefromDealer(String dealerID, String vehicleID) {
        String filepath=path+dealerID;
        String temppath=path+"Temp.txt";
        File inputFile=new File(filepath);
        File tempFile=new File(temppath);
        BufferedReader br=null;
        try{
            br=new BufferedReader(new FileReader(inputFile));

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        BufferedWriter bw=null;
        try{
            bw=new BufferedWriter(new FileWriter(tempFile));
        }catch (IOException ie){
            ie.printStackTrace();
        }
        String cur_line;
        try{
            if (br != null) {
                while((cur_line=br.readLine())!=null){
                    String trimmedLine=cur_line.trim();
                    if(trimmedLine.contains(vehicleID)) continue;
                    try{
                        if (bw != null) {
                            bw.write(cur_line);
                        }
                    }catch(IOException ie){
                        ie.printStackTrace();
                    }
                }
            }
        }catch(IOException ie2) {
            ie2.printStackTrace();
        }finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return tempFile.renameTo(inputFile);

    }
}
