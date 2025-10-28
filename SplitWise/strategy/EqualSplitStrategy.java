package org.example.SplitWise.strategy;


import org.example.SplitWise.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplitStrategy implements SplitStrategy {
    public Map<User, Double> split(double totalAmount, List<User> participants, Map<User, Double> meta) {
        double share = totalAmount / participants.size();
        Map<User, Double> splits = new HashMap<>();
        for (User user : participants) {
            splits.put(user, share);
        }
        return splits;
    }
}
