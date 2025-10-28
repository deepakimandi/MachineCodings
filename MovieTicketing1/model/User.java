package org.example.MovieTicketing1.model;

public class User {
    private final String name;
    private final String emailAddress;

    public User(final String name, final String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public String getUserName() {
        return name;
    }
    public String getUserEmail() {
        return emailAddress;
    }
}