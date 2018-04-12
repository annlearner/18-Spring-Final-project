package com.neuSpring18.service;

import com.neuSpring18.dao.DealerManager;
import com.neuSpring18.dto.Dealer;

import java.util.Collection;

public class DealerService implements IDealerService{
    @Override
    public Collection<Dealer> getAllDealers() {
        return new DealerManager().getAllDealers();
    }
}
