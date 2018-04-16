package com.neuSpring18.dto;

import java.util.ArrayList;
import java.util.Collection;

public class Inventory {
    private Collection<Vehicle> vehicles;
    private InventoryContext ic;

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Collection<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public InventoryContext getIc() {
        return ic;
    }

    public void setIc(InventoryContext ic) {
        this.ic = ic;
    }
}
