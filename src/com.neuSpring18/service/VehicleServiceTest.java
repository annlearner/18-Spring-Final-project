package com.neuSpring18.service;

import com.neuSpring18.dto.Filter;
import com.neuSpring18.dto.Paging;
import com.neuSpring18.dto.Sorting;
import com.neuSpring18.dto.Vehicle;

public class VehicleServiceTest {
    public static void main(String[] args) {


        VehicleService vs = new VehicleServiceImple();
        for (Vehicle vehicle : vs.getVehiclesByDealer("gmps-curry").getVehicles()) {
            System.out.println(vehicle);
        }

        System.out.println("--------------");

        Filter f = new Filter();
        f.setMinPrice("20000");
        Sorting s = Sorting.ASCEND_PRICE;
        Paging p = new Paging();
        p.setPageNum(1);
        p.setPerPage(10);
        for (Vehicle vehicle : vs.getVehiclesByFilter("gmps-curry", f, s, p).getVehicles()) {
            System.out.println(vehicle);
        }


//        String addString = "~gmps-curry~new~2018~Chevrolet~Equinox~AWD LS~SUV~28195.0~http://inventory-dmg.assets-cdk.com/3/3/3/14256931333x90.jpg";
//        String editString = "2970165824~gmps-curry~new~2018~Chevrolet~Equinox~AWD LS~SUV~28195.0~http://inventory-dmg.assets-cdk.com/3/3/3/14256931333x90.jpg";
//        Vehicle v = new Vehicle(addString);
//        System.out.println(vs.addVehicle("gmps-curry", v));
//        v = new Vehicle(editString);
//        System.out.println(vs.editVehicle("gmps-curry", v));
//        System.out.println(vs.removeVehicle("gmps-curry", v.getId()));
    }
}
