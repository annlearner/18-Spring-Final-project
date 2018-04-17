package com.neuSpring18.dao;

import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.InventoryContext;
import com.neuSpring18.dto.Vehicle;
import com.neuSpring18.io.UserIO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class VehicleManagerImple implements VehicleManager {

    @Override
    public Collection<Vehicle> searchVehiclesByFilter(String dealerID, Filter filter) {

        String search = filter.getSearch();
        String minPrice = filter.getMinPrice();
        String maxPrice = filter.getMaxPrice();
        String minYear = filter.getMinYear();
        String maxYear = filter.getMaxYear();
        String make = filter.getMake();
        String category = filter.getCategory();

        UserIO userIO = new UserIO();
        List<String> vehiclesFromDealer = userIO.getAllBasedOnMode("ID", dealerID);
        vehiclesFromDealer.remove(0);
        Collection<Vehicle> filteredVehicles = new ArrayList<Vehicle>();

        if (search != null && !search.equals("")) {

            for (String v : vehiclesFromDealer) {

                Vehicle vehicle = Vehicle.generateVehicle(v);
                if (searchFilter(vehicle, search))
                    filteredVehicles.add(vehicle);
            }

        } else {

            for (String v : vehiclesFromDealer) {

                Vehicle vehicle = Vehicle.generateVehicle(v);
                if (minPriceFilter(vehicle, minPrice) && maxPriceFilter(vehicle, maxPrice)
                        && minYearFilter(vehicle, minYear) && maxYearFilter(vehicle, maxYear)
                        && makeFilter(vehicle, make) && categoryFilter(vehicle, category)) {
                    filteredVehicles.add(vehicle);
                }
            }
        }
        return filteredVehicles;
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

    @Override
    public Collection<Vehicle> getVehiclesFromDealer(String dealerID) {

        UserIO userIO = new UserIO();
        List<String> vehiclesFromDealer = userIO.getAllBasedOnMode("ID", dealerID);
        vehiclesFromDealer.remove(0);
        Collection<Vehicle> vehicles = new ArrayList<Vehicle>();
        for (String v : vehiclesFromDealer) {
            vehicles.add(Vehicle.generateVehicle(v));
        }
        return vehicles;
    }

    @Override
    public InventoryContext getContext(String dealerID) {

        InventoryContext ic = new InventoryContext();
        HashSet<String> makeSet = new HashSet<>();
        HashSet<String> typeSet = new HashSet<>();
        Collection<Vehicle> vehicles = new ArrayList<>(getVehiclesFromDealer(dealerID));
        ic.setTotalCount(vehicles.size());

        for (Vehicle v : vehicles) {
            String make = v.getMake();
            String type = v.getBodyType();
            addToList(makeSet, make);
            addToList(typeSet, type);
        }

        ic.setMakes(new ArrayList<>(makeSet));
        ic.setTypes(new ArrayList<>(typeSet));
        return ic;
    }

    private static void addToList(HashSet<String> set, String string) {
        if (!set.contains(string))
            set.add(string);
    }

    @Override
    public String addVehicle(String dealerID, Vehicle v) {
        UserIO userIO = new UserIO();
        return userIO.addVehicleToDealer(dealerID, v.toString());
    }

    @Override
    public boolean editVehicle(String dealerID, Vehicle v) {
        UserIO userIO = new UserIO();
        return userIO.editVehicleofDealer(dealerID, v.getId(), v.toString());
    }

    @Override
    public boolean deleteVehicle(String dealerID, String vehicleID) {
        UserIO userIO = new UserIO();
        return userIO.deleteVehiclefromDealer(dealerID, vehicleID);
    }
}
