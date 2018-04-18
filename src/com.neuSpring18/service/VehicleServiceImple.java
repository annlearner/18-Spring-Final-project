package com.neuSpring18.service;

import com.neuSpring18.dao.VehicleManagerImple;
import com.neuSpring18.dto.*;

import java.util.*;

import static com.neuSpring18.dto.Sorting.ASCEND_PRICE;
import static com.neuSpring18.dto.Sorting.ASCEND_YEAR;
import static com.neuSpring18.dto.Sorting.DESCEND_PRICE;
import static com.neuSpring18.dto.Sorting.DESCEND_YEAR;
import static com.neuSpring18.dto.Sorting.DEFAULT;

public class VehicleServiceImple implements VehicleService {

    @Override
    public Inventory findVehiclesByFilter(String dealerID, Filter filter, Sorting sorting, Paging paging) {
        VehicleManagerImple vm = new VehicleManagerImple();
        Collection<Vehicle> vehicles = vm.searchVehiclesByFilter(dealerID,filter);
        Collection<Vehicle> afterSortAndPaging = new ArrayList<Vehicle>();
        List<Vehicle> vehicleList = new ArrayList<Vehicle>(vehicles);
        Inventory VehiclesInventory = new Inventory();

      
        if (sorting.equals(ASCEND_PRICE)) {
            Collections.sort(vehicleList, new Comparator<Vehicle>() {
                public int compare(Vehicle v1, Vehicle v2) {
                    if (v1.getPrice() < v2.getPrice())
                        return -1;
                    else if (v1.getPrice() == v2.getPrice())
                        return 0;
                    else
                        return 1;
                }
            });
        } else if (sorting.equals(DESCEND_PRICE)) {
            Collections.sort(vehicleList, new Comparator<Vehicle>() {
                public int compare(Vehicle v1, Vehicle v2) {
                    if (v1.getPrice() < v2.getPrice())
                        return 1;
                    else if (v1.getPrice() == v2.getPrice())
                        return 0;
                    else
                        return -1;
                }
            });
        } else if (sorting.equals(ASCEND_YEAR)) {
            Collections.sort(vehicleList, new Comparator<Vehicle>() {
                public int compare(Vehicle v1, Vehicle v2) {
                    if (v1.getYear() < v2.getYear())
                        return -1;
                    else if (v1.getYear() == v2.getYear())
                        return 0;
                    else
                        return 1;
                }
            });
        } else if (sorting.equals(DESCEND_YEAR)) {
            Collections.sort(vehicleList, new Comparator<Vehicle>() {
                public int compare(Vehicle v1, Vehicle v2) {
                    if (v1.getYear() < v2.getYear())
                        return 1;
                    else if (v1.getYear() == v2.getYear())
                        return 0;
                    else
                        return -1;
                }
            });
        } else if (sorting.equals(DEFAULT)) {
            Collections.sort(vehicleList, new Comparator<Vehicle>() {
                public int compare(Vehicle v1, Vehicle v2) {
                    if (Long.parseLong(v1.getId()) < Long.parseLong(v2.getId()))
                        return -1;
                    else if (Long.parseLong(v1.getId()) == Long.parseLong(v2.getId()))
                        return 0;
                    else
                        return 1;
                }
            });
        }

        int start = paging.getPageNum() * paging.getPerPage() - paging.getPerPage();
        int end;
        if (paging.getPageNum() * paging.getPerPage() > vehicleList.size()) {
            end = vehicleList.size();
        } else
            end = paging.getPageNum() * paging.getPerPage();
        for (int i = start; i < end; i++) {
            afterSortAndPaging.add(vehicleList.get(i));
        }

        VehiclesInventory.setVehicles(afterSortAndPaging);
        VehiclesInventory.setIc(vm.getContext(dealerID));
        return VehiclesInventory;
    }


    @Override
    public Inventory findVehiclesByDealer(String dealerID) {
        Inventory allVehiclesInventory = new Inventory();
        VehicleManagerImple vm = new VehicleManagerImple();
        Collection<Vehicle> vehicles = vm.getVehiclesFromDealer(dealerID);
        allVehiclesInventory.setIc(vm.getContext(dealerID));
        allVehiclesInventory.setVehicles(vehicles);
        return allVehiclesInventory;
    }

    @Override
    public String addVehicle(String dealerID, Vehicle v) {
        VehicleManagerImple vm = new VehicleManagerImple();
        return vm.addVehicle(dealerID,v);
    }

    @Override
    public boolean editVehicle(String dealerID, Vehicle v) {
        VehicleManagerImple vm = new VehicleManagerImple();
        return vm.editVehicle(dealerID,v);
    }

    @Override
    public boolean removeVehicle(String dealerID, String vehicleID) {
        VehicleManagerImple vm = new VehicleManagerImple();
        return vm.deleteVehicle(dealerID,vehicleID);
    }
}
