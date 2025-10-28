package org.example.CustomerIssueResolution1.model.split;

import org.example.CustomerIssueResolution1.model.User;

public class ExactSplit extends Split {

    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }

}
