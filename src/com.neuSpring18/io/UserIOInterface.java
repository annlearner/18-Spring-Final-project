package com.neuSpring18.io;

import java.util.List;

public interface UserIOInterface {
    List<String> getAllBasedOnMode(String mode,String Filename);//mode:ID|All
    String addVehicleToDealer(String dealerID, String vehicleString);
    boolean editVehicleOfDealer(String dealerID,String vehicleID, String vehicleString);
    boolean deleteVehicleFromDealer(String dealerID, String vehicleID);
}
