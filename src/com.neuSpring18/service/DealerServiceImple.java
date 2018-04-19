package com.neuSpring18.service;

import com.neuSpring18.dao.DealerManagerImple;
import com.neuSpring18.dto.Dealer;

import java.util.Collection;

public class DealerServiceImple implements DealerService {
    @Override
    public Collection<Dealer> findAllDealers() {
        return new DealerManagerImple().getAllDealers();
    }

    @Override
    public Dealer logIn(String id, String password) {
        return new DealerManagerImple().logIn(id, password);
    }
}
