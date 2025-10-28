package org.example.CustomerIssueResolution1.model;

public class User {
    int userId;
    String userName;
    String email;
    String mobileNumber;

    public User(String userName, int userId, String email, String mobileNumber) {
        this.userName = userName;
        this.userId = userId;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
