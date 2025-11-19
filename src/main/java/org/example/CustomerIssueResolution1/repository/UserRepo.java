package org.example.CustomerIssueResolution1.repository;

import org.example.CustomerIssueResolution1.model.User;

import java.util.HashMap;

public class UserRepo {
    private HashMap<String, User> users;

    public UserRepo() {
        this.users = new HashMap<>();
    }
}
