package com.neuSpring18.dao;

import com.neuSpring18.dto.Dealer;
import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Vehicle;
import com.neuSpring18.io.VehicleIO;

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
        return VehicleIO.addVehicleToDealer(dealerID, v.toString());
    }

    @Override
    public boolean editVehicle(Vehicle v) {
        return VehicleIO.editVehicleOfDealer(dealerID, v.toString());
    }

    @Override
    public boolean deleteVehicle(String vehicleID) {
        return VehicleIO.deleteVehicleFromDealer(dealerID, vehicleID);
    }

    public void setDealer(String dealerID) {
        this.dealerID = dealerID;
    }
}
