package com.neuSpring18.dao;

import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Vehicle;
import com.neuSpring18.io.VehicleIO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VehicleManagerImple implements VehicleManager {

    private String dealerID;
    public VehicleManagerImple(String dealerID) {
        this.dealerID = dealerID;
    }

    @Override
    public Collection<Vehicle> getVehicleFromDealer(Filter filter) {

        String minPrice = filter.getMinPrice();
        String maxPrice = filter.getMaxPrice();
        String minYear = filter.getMinYear();
        String maxYear = filter.getMaxYear();
        String make = filter.getMake();
        String category = filter.getCategory();
        String search = filter.getSearch();

        List<String> vehiclesFromDealer = VehicleIO.getVehiclesFromDealer(dealerID);
        vehiclesFromDealer.remove(0);
        Collection<Vehicle> filteredVehicles = new ArrayList<Vehicle>();

        for (String v : vehiclesFromDealer) {

            Vehicle vehicle = new Vehicle(v);
            if (minPriceFilter(vehicle, minPrice) && maxPriceFilter(vehicle, maxPrice)
                    && minYearFilter(vehicle, minYear) && maxYearFilter(vehicle, maxYear)
                    && makeFilter(vehicle, make) && categoryFilter(vehicle, category)
                    && searchFilter(vehicle, search)) {
                filteredVehicles.add(vehicle);
            }
        }
        return filteredVehicles;
    }

    private boolean minPriceFilter(Vehicle vehicle, String minPrice) {

        if (minPrice == null || minPrice.equals(""))
            return true;
        double vehiclePrice = vehicle.getPrice();
        double min = Double.parseDouble(minPrice);
        if (vehiclePrice >= min)
            return true;

        return false;
    }

    private boolean maxPriceFilter(Vehicle vehicle, String maxPrice) {

        if (maxPrice == null || maxPrice.equals(""))
            return true;

        double vehiclePrice = vehicle.getPrice();
        double max = Double.parseDouble(maxPrice);
        if (vehiclePrice <= max)
            return true;

        return false;
    }

    private boolean minYearFilter(Vehicle vehicle, String minYear) {

        if (minYear == null || minYear.equals(""))
            return true;

        int vehicleYear = vehicle.getYear();
        int min = Integer.parseInt(minYear);
        if (vehicleYear >= min)
            return true;

        return false;
    }

    private boolean maxYearFilter(Vehicle vehicle, String maxYear) {

        if (maxYear == null || maxYear.equals(""))
            return true;

        int vehicleYear = vehicle.getYear();
        int max = Integer.parseInt(maxYear);
        if (vehicleYear <= max)
            return true;

        return false;
    }

    private boolean makeFilter(Vehicle vehicle, String make) {

        if (make == null || make.equals(""))
            return true;
        return vehicle.getMake().contains(make);
    }

    private boolean categoryFilter(Vehicle vehicle, String category) {

        if (category == null || category.equals(""))
            return true;
        return vehicle.getCategory().toString().contains(category);
    }

    private boolean searchFilter(Vehicle vehicle, String search) {

        if (search == null || search.equals(""))
            return true;

        search = search.toLowerCase();
        String vehicleString = vehicle.toSearchString().toLowerCase();
        String[] arr = search.split(" +");

        for (String s : arr) {
            if (!vehicleString.contains(s))
                return false;
        }

        return true;
    }

    @Override
    public Collection<Vehicle> getVehicleFromDealer() {
        List<String> vehiclesFromDealer = VehicleIO.getVehiclesFromDealer(dealerID);
        vehiclesFromDealer.remove(0);
        Collection<Vehicle> vehicles = new ArrayList<Vehicle>();
        for (String v : vehiclesFromDealer) {
            vehicles.add(new Vehicle(v));
        }
        return vehicles;
    }

    @Override
    public String addVehicle(Vehicle v) {
        return VehicleIO.addVehicleToDealer(dealerID, v.toString());
    }

    @Override
    public boolean editVehicle(Vehicle v) {
        return VehicleIO.editVehicleOfDealer(dealerID, v.getId(), v.toString());
    }

    @Override
    public boolean deleteVehicle(String vehicleID) {
        return VehicleIO.deleteVehicleFromDealer(dealerID, vehicleID);
    }

    public void setDealer(String dealerID) {
        this.dealerID = dealerID;
    }
}
