package com.neuSpring18.service;

import com.neuSpring18.dao.VehicleManagerImple;
import com.neuSpring18.dto.*;

import java.util.*;

import static com.neuSpring18.dto.Sorting.ASCEND_PRICE;
import static com.neuSpring18.dto.Sorting.ASCEND_YEAR;
import static com.neuSpring18.dto.Sorting.DESCEND_PRICE;

public class VehicleServiceImple implements VehicleService {

    @Override
    public Inventory findVehiclesByFilter(String dealerID, Filter filter, Sorting sorting, Paging paging) {
        VehicleManagerImple vm = new VehicleManagerImple();//need to change the input depend on dto
        Collection<Vehicle> vehicles = vm.searchVehiclesByFilter(dealerID,filter);
        Collection<Vehicle> afterSortAndPaging = new ArrayList<Vehicle>();
        List<Vehicle> vehicleList = new ArrayList<Vehicle>(vehicles);
        Inventory vehiclesInventory = new Inventory();
        Inventory allVehiclesInventory = new Inventory();
        int numPerPage = paging.getPerPage();


        if (sorting.equals(ASCEND_PRICE)) {
            Collections.sort(vehicleList, new Comparator<Vehicle>() {
                public int compare(Vehicle v1, Vehicle v2) {
                    if (v1.getPrice() <= v2.getPrice())
                        return -1;
                    else
                        return 1;
                }
            });
        } else if (sorting.equals(DESCEND_PRICE)) {
            {
                Collections.sort(vehicleList, new Comparator<Vehicle>() {
                    public int compare(Vehicle v1, Vehicle v2) {
                        if (v1.getPrice() <= v2.getPrice())
                            return 1;
                        else
                            return -1;
                    }
                });
            }
        } else if (sorting.equals(ASCEND_YEAR)) {
            Collections.sort(vehicleList, new Comparator<Vehicle>() {
                public int compare(Vehicle v1, Vehicle v2) {
                    if (v1.getYear() <= v2.getYear())
                        return -1;
                    else
                        return 1;
                }
            });
        }else if(sorting.equals(Default)){
            Collections.sort(vehicleList, new Comparator<Vehicle>() {
                public int compare(Vehicle v1, Vehicle v2) {
                    if (Integer.parseInt(v1.getId()) <= Integer.parseInt( v2.getId()))
                        return -1;
                    else
                        return 1;
                }
            });}
        int totalPage = 0;
        if(vehicleList.size() % paging.getPerPage() == 0 )
            totalPage = vehicleList.size() / paging.getPerPage();
        else
            totalPage = (vehicleList.size() / paging.getPerPage()) + 1;

        int start = paging.getPageNum() * paging.getPerPage() - paging.getPerPage();
        int end;
        if (paging.getPageNum() * paging.getPerPage() > vehicleList.size()) {
            end = vehicleList.size();
        } else
            end = paging.getPageNum() * paging.getPerPage();
        for (int i = start; i < end; i++) {
            afterSortAndPaging.add(vehicleList.get(i));
        }

        allVehiclesInventory.setVehicles(afterSortAndPaging);
        allVehiclesInventory.setIc(vm.getContext(dealerID));
        return allVehiclesInventory;
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
