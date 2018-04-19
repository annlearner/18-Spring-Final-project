package com.neuSpring18.service;

import com.neuSpring18.dto.Dealer;

import java.util.Collection;

public interface DealerService {
    Collection<Dealer> findAllDealers();
    Dealer logIn(String id, String password); // Return a dealer object if success, return null if failed
}
