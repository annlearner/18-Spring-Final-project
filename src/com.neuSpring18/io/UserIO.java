package com.neuSpring18.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserIO implements UserIOInterface {
    private String path=System.getProperty("user.dir")+File.separatorChar+"data"+File.separatorChar;
    private BufferedWriter bw=null;
    private BufferedReader br=null;

    @Override
    public List<String> getAllBasedOnMode(String mode, String filename) {
        String file=path+filename;
        List<String> result = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(file));
            String read;
            String[] splited;
            while ((read = br.readLine()) != null) {
                if (mode.equals("ID")) {

                    if(filename.equals("dealers")) {
                        splited = read.split("\t");
                    }else {
                        splited=read.split("~");
                    }

                    if (splited.length > 0)
                        result.add(splited[0]);

                } else if (mode.equals("All"))
                    result.add(read);
            }
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
        String newID=null;
        //hasID no? then generate Id
        if(!hasId(dealerID,newID)) {
            newID = generateId();
        }
        //add the info to file
        FileWriter fw=null;
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
                pw.println(newID+"~"+dealerID+"~"+vehicleString);
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
    public boolean editVehicleOfDealer(String dealerID, String vehicleID, String vehicleString) {
        boolean success=true;
        String f=path+dealerID;
        File file=new File(f);
        String target=vehicleID;
        String replace=vehicleID+"~"+dealerID+"~"+vehicleString;
        FileWriter fw = null;

        try {
            br=new BufferedReader(new FileReader(file));
            String line;
            String wholeline=" ";
            while((line=br.readLine())!=null) {
                wholeline+=line+System.lineSeparator();
            }
            wholeline=wholeline.replace(target,replace);
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
