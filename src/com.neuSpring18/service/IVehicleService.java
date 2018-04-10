package com.neuSpring18.service;

import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Vehicle;

import java.util.Collection;

public interface IVehicleService {
    Collection<Vehicle> getVehiclesByFilter(Filter filter);
    Collection<Vehicle> getVehiclesByDealer(String dealerID);
    String addVehicle(Vehicle v); // Return the ID of the new vehicle
    boolean editVehicle(Vehicle v); // Return true if edit successfully. Notice that we might add some error control later.
    boolean removeVehicle(String vehicleID); // Might add error control as well.
}
