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
        ArrayList<Vehicle> vehicleList = new ArrayList<>(vehicles);
        Inventory VehiclesInventory = new Inventory();

        vehicleList = sorting(vehicleList,sorting);
        afterSortAndPaging = paging(vehicleList, paging);

        VehiclesInventory.setVehicles(afterSortAndPaging);
        VehiclesInventory.setIc(vm.getContext(dealerID, filter));
        return VehiclesInventory;
    }

    private ArrayList<Vehicle> sorting(ArrayList<Vehicle> list, Sorting sorting ){
        if (sorting.equals(ASCEND_PRICE)) {
            Collections.sort(list, new Comparator<Vehicle>() {
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
            Collections.sort(list, new Comparator<Vehicle>() {
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
            Collections.sort(list, new Comparator<Vehicle>() {
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
            Collections.sort(list, new Comparator<Vehicle>() {
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
            Collections.sort(list, new Comparator<Vehicle>() {
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
        return list;
    }

    private ArrayList<Vehicle> paging(ArrayList<Vehicle> list, Paging paging ){
        ArrayList<Vehicle> res = new ArrayList<>();
        int start = paging.getPageNum() * paging.getPerPage() - paging.getPerPage();
        int end;
        if (paging.getPageNum() * paging.getPerPage() > list.size()) {
            end = list.size();
        } else
            end = paging.getPageNum() * paging.getPerPage();
        for (int i = start; i < end; i++) {
            res.add(list.get(i));
        }
        return res;
    }

    @Override
    public Inventory findVehiclesByDealer(String dealerID) {
        Inventory allVehiclesInventory = new Inventory();
        VehicleManagerImple vm = new VehicleManagerImple();
        Collection<Vehicle> vehicles = vm.getVehiclesFromDealer(dealerID);
        allVehiclesInventory.setIc(vm.getContext(dealerID, new Filter()));
        allVehiclesInventory.setVehicles(vehicles);
        return allVehiclesInventory;
    }

    @Override
    public String addVehicle(String dealerID, Vehicle v) {
        if (v.getId() != null || !v.getId().equals("")) {
            v.setId("");
        }
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
