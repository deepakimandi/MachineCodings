package org.example.SplitWise.strategy;


import org.example.SplitWise.model.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    Map<User, Double> split(double totalAmount, List<User> participants, Map<User, Double> meta);
}
