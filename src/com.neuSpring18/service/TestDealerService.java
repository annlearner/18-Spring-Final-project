package com.neuSpring18.service;

public class TestDealerService {
    public static void main(String[] args) {
        IDealerService ds = new DealerService();
        System.out.println(ds.getAllDealers());
    }
}
