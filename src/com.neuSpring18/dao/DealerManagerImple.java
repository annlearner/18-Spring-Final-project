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
            result.add(Dealer.generateByString(d));
        }
        return result;
    }

    @Override
    public Dealer logIn(String id, String password) {
        UserIOInterface io = new UserIO();
        List<String> dealers = io.getAllBasedOnMode("All", "dealers");
        for (String d : dealers) {
            String[] ss = d.split("\t");
            if (id.equals(ss[0]) && password.equals(ss[3])) {
                return Dealer.generateByString(d);
            }
        }
        return null;
    }
}
