package com.neuSpring18.dto;

import java.util.Comparator;

public enum Sorting {

//    Here we use the Comparator.comparingDouble(Vehicle::getPrice()) method, which is short for:
//    Comparator.comparingDouble((Vehicle v) -> v.getPrice());
//    which is short for:
//    (a, b) -> { return Double.compare(a.getPrice(), b.getPrice()); }
//    which is short for:
//    class Sorter implements Comparator<Vehicle> {
//        @Override
//        public int compare(Vehicle a, Vehicle b) {
//            return Double.compare(a.getPrice(), b.getPrice());
//        }
//    }
//    which is extremely elegant!!!

    DEFAULT(Comparator.comparing(Vehicle::getId)),
    ASCEND_PRICE(Comparator.comparingDouble(Vehicle::getPrice)),
    DESCEND_PRICE(Comparator.comparingDouble(Vehicle::getPrice).reversed()),
    ASCEND_YEAR(Comparator.comparingInt(Vehicle::getYear)),
    DESCEND_YEAR(Comparator.comparingInt(Vehicle::getYear).reversed());

    private final Comparator<Vehicle> sorter;

    Sorting(Comparator<Vehicle> sorter) {
        this.sorter = sorter;
    }

    public Comparator<Vehicle> getSorter() {
        return sorter;
    }


}
