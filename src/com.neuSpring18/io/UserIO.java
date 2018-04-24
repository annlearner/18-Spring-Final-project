package com.neuSpring18.io;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserIO implements UserIOInterface {
    private String path=System.getProperty("user.dir")+File.separatorChar+"data"+File.separatorChar;
    private BufferedWriter bw=null;
    private BufferedReader br=null;

    @Override
    public List<String> getAllBasedOnMode(String mode, String filename) {
        String f=path+filename;
        File file=new File(f);
        List<String> result = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(file));
            String read;
            String[] splited;
            while ((read = br.readLine()) != null) {
                if (mode.equals("ID")) {
                    if(filename.equals("dealers")) splited = read.split("\t");
                    else splited=read.split("~");
                    if (splited.length > 0) result.add(splited[0]);
                } else if (mode.equals("All"))
                    result.add(read); }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            closeReader(br);
        }
        return result;
    }


    @Override
    public String addVehicleToDealer(String dealerID, String vehicleString) {
        String selected=path+dealerID;
        File file=new File(selected);
        String newID;
        while(true){
           newID=generateId();
           if(!hasId(dealerID,newID))  break;
        }
        //add the info to file
        FileWriter fw=null;
        try {
            fw=new FileWriter(file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fw != null) bw=new BufferedWriter(fw);
        if (bw != null) {
            try (PrintWriter pw = new PrintWriter(bw)) {
                pw.println(newID+vehicleString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newID;
    }

    @Override
    public boolean addPictures(String dealerID, String picString) {
        File file=new File(path+dealerID);
        FileWriter fw=null;
        try {
            fw=new FileWriter(file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (fw != null) bw=new BufferedWriter(fw);
        if (bw != null) {
            try (PrintWriter pw = new PrintWriter(bw)) {
                pw.println(picString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * Generate an ID randomly by using UUID
     * @return the ID
     */

    private String generateId() {
        //generate a 37 digit ID
        return String.format("%d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));


        /*
        // return a 10 digit ID
        int x=0;
		String ran=String.format("%d",new BigInteger(UUID.randomUUID().toString().replace("-",""),16));
		while(true) {
		x=(int) (Math.random()*ran.length());
		if(x+10<ran.length()-1) break;
		}
        return ran.substring(x,x+10));
        */

    }

    private boolean hasId(String filename,String ID) {
        List<String> result=getAllBasedOnMode("ID",filename);
        return result.contains(ID);
    }

    @Override
    public boolean editVehicleOfDealer(String dealerID, String vehicleID, String vehicleString) {
        boolean success=true;
        String f=path+dealerID;
        File file=new File(f);
        FileWriter fw = null;
        try {
            br=new BufferedReader(new FileReader(file));
            String line;
            String wholeline="";
            while((line=br.readLine())!=null) {
                if(!line.contains(vehicleID)) {
                    success=false;
                    break;
                }
                else {
                    Pattern pattern=Pattern.compile("\\w.*");
                    Matcher matcher=pattern.matcher(line) ;
                    line=matcher.replaceAll(vehicleString);
                }
                wholeline+=line+System.lineSeparator();
            }
            fw=new FileWriter(file);
            fw.write(wholeline);
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            closeReader(br);
            closeWriter(fw);
        }
        return success;
    }


    @Override
    public boolean deleteVehicleFromDealer(String dealerID, String vehicleID) {
        String cur_line;
        boolean successful=false;
        String filepath=path+dealerID;
        String temppath=path+"Temp";
        File inputFile=new File(filepath);
        File tempFile=new File(temppath);
        try {
            br=new BufferedReader(new FileReader(inputFile));
            bw=new BufferedWriter(new FileWriter(tempFile));
            while((cur_line=br.readLine())!=null) {
                if(cur_line.trim().contains(vehicleID)) continue;
                bw.write(cur_line+System.getProperty("line.separator"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                inputFile.delete();
                closeReader(br);
                closeWriter(bw);
                inputFile.delete();
                successful=tempFile.renameTo(inputFile);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
        return successful;
    }

    private void closeReader(Reader r){
        try {
            if(r!=null) {
                r.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeWriter(Writer w){
        try {
            if(w!=null) {
                w.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
