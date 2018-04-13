package com.neuSpring18.service;

import com.neuSpring18.dao.VehicleManager;
import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Paging;
import com.neuSpring18.dto.Sorting;
import com.neuSpring18.dto.Vehicle;

import java.util.*;

import static com.neuSpring18.dto.Sorting.ASCEND_PRICE;
import static com.neuSpring18.dto.Sorting.ASCEND_YEAR;
import static com.neuSpring18.dto.Sorting.DESCEND_PRICE;

public class VehicleService implements IVehicleService {

    private VehicleManager vm;
    Collection<Vehicle> temp;
    Collection<Vehicle> afterSortAndPaging = new ArrayList<Vehicle>();
    @Override
    public Collection<Vehicle> getVehiclesByFilter(String dealerID, Filter filter, Sorting sorting, Paging paging) {
        vm = new VehicleManager(dealerID);
        temp = vm.getVehicleFromDealer(filter);
        List<Vehicle> list = new ArrayList<Vehicle>(temp);
        int numPerPage = paging.getPerPage();


        if (sorting.equals(ASCEND_PRICE)) {
            Collections.sort(list, new Comparator<Vehicle>() {
                public int compare(Vehicle v1, Vehicle v2) {
                    if (v1.getPrice() <= v2.getPrice())
                        return -1;
                    else
                        return 1;
                }
            });
        } else if (sorting.equals(DESCEND_PRICE)) {
            {
                Collections.sort(list, new Comparator<Vehicle>() {
                    public int compare(Vehicle v1, Vehicle v2) {
                        if (v1.getPrice() <= v2.getPrice())
                            return 1;
                        else
                            return -1;
                    }
                });
            }
        } else if (sorting.equals(ASCEND_YEAR)) {
            Collections.sort(list, new Comparator<Vehicle>() {
                public int compare(Vehicle v1, Vehicle v2) {
                    if (v1.getYear() <= v2.getYear())
                        return -1;
                    else
                        return 1;
                }
            });
        }

        int start = paging.getPageNum() * paging.getPerPage() - paging.getPerPage();
        int end;
        if (paging.getPageNum() * paging.getPerPage() > list.size()) {
            end = list.size();
        } else
            end = paging.getPageNum() * paging.getPerPage();
        for (int i = start; i < end; i++) {
            afterSortAndPaging.add(list.get(i));
        }
        return afterSortAndPaging;

    }

    @Override
    public Collection<Vehicle> getVehiclesByDealer(String dealerID) {
        vm = new VehicleManager(dealerID);
        return vm.getVehicleFromDealer();
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
