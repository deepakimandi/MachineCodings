package org.example.CustomerIssueResolution1.model;

import java.time.LocalDateTime;

public class Address {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private User user;
    private final LocalDateTime createdAt = LocalDateTime.now();
}
