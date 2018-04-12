package com.neuSpring18.service;

import com.neuSpring18.dto.Vehicle;

public class TestVehicleService {
    public static void main(String[] args) {
        String addString = "~gmps-curry~new~2018~Chevrolet~Equinox~AWD LS~SUV~28195.0~http://inventory-dmg.assets-cdk.com/3/3/3/14256931333x90.jpg";
        String editString = "2970165823~gmps-curry~new~2018~Chevrolet~Equinox~AWD LS~SUV~28195.0~http://inventory-dmg.assets-cdk.com/3/3/3/14256931333x90.jpg";
        Vehicle v = new Vehicle(addString);
        IVehicleService vs = new VehicleService();
        System.out.println(vs.addVehicle("gmps-curry", v));
        v = new Vehicle(editString);
        System.out.println(vs.editVehicle("gmps-curry", v));
        System.out.println(vs.removeVehicle("gmps-curry", v.getId()));
    }
}
