package com.neuSpring18.service;

public class DealerServiceTest {
    public static void main(String[] args) {
        System.out.println("1. Find all dealers");
        DealerService ds = new DealerServiceImple();
        System.out.println(ds.findAllDealers());

        System.out.println("--------------");

        System.out.println("2. Log in with id and password");
        System.out.println(ds.logIn("gmps-curry", "123456"));
    }
}
