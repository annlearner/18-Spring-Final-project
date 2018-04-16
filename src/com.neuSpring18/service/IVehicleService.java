package com.neuSpring18.service;

import com.neuSpring18.dto.*;

import java.util.Collection;

public interface IVehicleService {
    Inventory getVehiclesByFilter(String dealerID, Filter filter, Sorting sorting, Paging paging);
    Inventory getVehiclesByDealer(String dealerID);
    String addVehicle(String dealerID, Vehicle v); // Return the ID of the new vehicle
    boolean editVehicle(String dealerID, Vehicle v); // Return true if edit successfully. Notice that we might add some error control later.
    boolean removeVehicle(String dealerID, String vehicleID); // Might add error control as well.
}
