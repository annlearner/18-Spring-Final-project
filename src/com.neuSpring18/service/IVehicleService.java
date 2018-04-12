package com.neuSpring18.service;

import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Paging;
import com.neuSpring18.dto.Sorting;
import com.neuSpring18.dto.Vehicle;

import java.util.Collection;

public interface IVehicleService {
    Collection<Vehicle> getVehiclesByFilter(String dealerID, Filter filter, Sorting sorting, Paging paging);
    Collection<Vehicle> getVehiclesByDealer(String dealerID);
    String addVehicle(String dealerID, Vehicle v); // Return the ID of the new vehicle
    boolean editVehicle(String dealerID, Vehicle v); // Return true if edit successfully. Notice that we might add some error control later.
    boolean removeVehicle(String dealerID, String vehicleID); // Might add error control as well.
}
