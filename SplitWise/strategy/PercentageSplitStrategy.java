package org.example.SplitWise.strategy;


import org.example.SplitWise.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentageSplitStrategy implements SplitStrategy {
    public Map<User, Double> split(double totalAmount, List<User> participants, Map<User, Double> meta) {
        Map<User, Double> splits = new HashMap<>();
        for (User user : meta.keySet()) {
            splits.put(user, totalAmount * meta.getOrDefault(user, 0.0) / 100);
        }
        return splits;
    }

}
