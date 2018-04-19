package com.neuSpring18.dto;

public enum CarType {

    VAN, WAGON, CAR, SUV, TRUCK, CARGOVAN, COMMERCIALVEHICLE, NOTYPE;

    public static CarType getType(String t) {

        switch(t) {

            case("VAN"): return VAN;
            case("WAGON"): return WAGON;
            case("CAR"): return CAR;
            case("SUV"): return SUV;
            case("TRUCK"): return TRUCK;
            case("CARGO VAN"): return CARGOVAN;
            case("COMMERCIAL VEHICLE"): return COMMERCIALVEHICLE;
            case(""): return NOTYPE;
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {

        switch (this) {
            case VAN:
                return "VAN";
            case WAGON:
                return "WAGON";
            case CAR:
                return "CAR";
            case SUV:
                return "SUV";
            case TRUCK:
                return "TRUCK";
            case CARGOVAN:
                return "CARGO VAN";
            case COMMERCIALVEHICLE:
                return "COMMERCIAL VEHICLE";
            case NOTYPE:
                return "NO TYPE";
            default: throw new IllegalArgumentException();
        }
    }
}
