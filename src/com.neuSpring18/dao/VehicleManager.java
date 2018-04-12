package com.neuSpring18.dao;

import com.neuSpring18.dto.Dealer;
import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Vehicle;

import java.util.Collection;

public class VehicleManager implements IVehicleManager{
    private String dealerID;
    public VehicleManager(String dealerID) {
        this.dealerID = dealerID;
    }

    @Override
    public Collection<Vehicle> getVehicleFromDealer(Filter filter) {
        return null;
    }

    @Override
    public Collection<Vehicle> getVehicleFromDealer() {
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
    public boolean deleteVehicle(String vehicleID) {
        return false;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }
}
