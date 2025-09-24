package com.elevatorpitch.dto;

public class UserRegistrationRequest {
    private String name;
    private String phoneNumber;
    
    public UserRegistrationRequest() {}
    
    public UserRegistrationRequest(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}