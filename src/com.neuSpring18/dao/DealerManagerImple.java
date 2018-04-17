package com.neuSpring18.dao;

import com.neuSpring18.dto.Dealer;
import com.neuSpring18.io.UserIO;
import com.neuSpring18.io.UserIOInterface;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DealerManagerImple implements DealerManager {
    @Override
    public Collection<Dealer> getAllDealers() {
        UserIOInterface io = new UserIO();
        List<String> dealers = io.getAllBasedOnMode("All", "dealers");
        List<Dealer> result = new LinkedList<>();
        for (String d : dealers) {
            result.add(new Dealer(d));
        }
        return result;
    }
}
