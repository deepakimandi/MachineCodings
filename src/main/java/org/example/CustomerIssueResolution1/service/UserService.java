package org.example.CustomerIssueResolution1.service;

import org.example.CustomerIssueResolution1.model.User;
import org.example.CustomerIssueResolution1.repository.UserRepo;

public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(User user) {

    }
}
