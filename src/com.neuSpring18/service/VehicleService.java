package com.neuSpring18.service;

import com.neuSpring18.dao.VehicleManager;
import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Paging;
import com.neuSpring18.dto.Sorting;
import com.neuSpring18.dto.Vehicle;

import java.util.Collection;

public class VehicleService implements IVehicleService {

    @Override
    public Collection<Vehicle> getVehiclesByFilter(String dealerID, Filter filter, Sorting sorting, Paging paging) {
        return null;
    }

    @Override
    public Collection<Vehicle> getVehiclesByDealer(String dealerID) {
        return null;
    }

    @Override
    public String addVehicle(String dealerID, Vehicle v) {
        VehicleManager vm = new VehicleManager(dealerID);
        return vm.addVehicle(v);
    }

    @Override
    public boolean editVehicle(String dealerID, Vehicle v) {
        VehicleManager vm = new VehicleManager(dealerID);
        return vm.editVehicle(v);
    }

    @Override
    public boolean removeVehicle(String dealerID, String vehicleID) {
        VehicleManager vm = new VehicleManager(dealerID);
        return vm.deleteVehicle(vehicleID);
    }
}
