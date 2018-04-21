package com.neuSpring18.dao;

import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.InventoryContext;
import com.neuSpring18.dto.Vehicle;

import java.util.Collection;

public interface VehicleManager {
    Collection<Vehicle> searchVehiclesByFilter(String dealerID, Filter filter); // We do not do any sort or paging in this layer, just simply return all valid results.
    Collection<Vehicle> getVehiclesFromDealer(String dealerID);
    InventoryContext getContext(String dealerID, Filter filter);
    String addVehicle(String dealerID, Vehicle v);
    boolean editVehicle(String dealerID, Vehicle v);
    boolean deleteVehicle(String dealerID, String vehicleID);
}
