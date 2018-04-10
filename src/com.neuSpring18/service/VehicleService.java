package com.neuSpring18.service;

import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Vehicle;

import java.util.Collection;

public class VehicleService implements IVehicleService {
    @Override
    public Collection<Vehicle> getVehiclesByFilter(Filter filter) {
        return null;
    }

    @Override
    public Collection<Vehicle> getVehiclesByDealer(String dealerID) {
        return null;
    }

    @Override
    public String addVehicle(Vehicle v) {
        return null;
    }

    @Override
    public boolean editVehicle(Vehicle v) {
        return false;
    }

    @Override
    public boolean removeVehicle(String vehicleID) {
        return false;
    }
}
