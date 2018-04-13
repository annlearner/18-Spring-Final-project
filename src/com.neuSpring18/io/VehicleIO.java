package com.neuSpring18.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleIO {

    private static String pathway="C:\\Users\\YueTa\\IdeaProjects\\18-Spring-Final-project\\data\\";
    /**
     * return all vehicles from selected Dealer
     * @param dealerID selected dealer
     * @return all vehicles' infomation
     */
    public static List<String> getVehiclesFromDealer(String dealerID) {
        List<String> vehicles = new ArrayList<>();
        String file = pathway+ dealerID;
        BufferedReader br = null;
        try {
            File f = new File(file);
            FileReader fr = new FileReader(f);
            br = new BufferedReader(fr);
            String line ;
            while ((line = br.readLine()) != null) {
                vehicles.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return vehicles;
    }

    /**
     * add the infomation of vehicle to its dealer's file
     * and generate an id for this new vehicle then return the ID
     * @param dealerID to select the dealer file
     * @param vehicleString the infomation about the new vehicle
     * @return the ID auto-generated
     */
    public static String addVehicleToDealer(String dealerID, String vehicleString) {
        String selected=pathway+dealerID;
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
    private static String generateId() {
        long number=(long)Math.floor(Math.random()*9_000_000_000L)+1_000_000_000L;
        return Long.toString(number);
    }

    private static boolean hasId(String filename,String ID) {
        List<String> result=getallVehicleID(filename);
        return result.contains(ID);
    }

    private static List<String> getallVehicleID(String filename){
        List<String> result= new ArrayList<>();
        BufferedReader br=null;
        try {
            br=new BufferedReader(new FileReader(filename));
            String read;
            while((read=br.readLine())!=null) {
                String[] splited = read.split("\t");
                if (splited.length > 0) {
                    result.add(splited[0]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.out.println("Process close has problems.");
                e.printStackTrace();
            }
        }
        return result;
    }
    public static boolean editVehicleOfDealer(String dealerID,String vehicleID, String vehicleString) {
        String filepath=pathway+dealerID;
        List<String> lines= new ArrayList<>();
        String line;
        try{
            File f=new File(filepath);
            FileReader fr=new FileReader(f);
            BufferedReader br=new BufferedReader(fr);
            while((line=br.readLine())!=null) {
                if (getallVehicleID(dealerID).size() == 0) {
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

    public static boolean deleteVehicleFromDealer(String dealerID, String vehicleID) {
        String filepath=pathway+dealerID;
        String temppath=pathway+"Temp.txt";
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
