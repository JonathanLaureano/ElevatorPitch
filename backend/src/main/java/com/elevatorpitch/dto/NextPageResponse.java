package com.elevatorpitch.dto;

public class NextPageResponse {
    private String nextPage;
    private String message;
    
    public NextPageResponse() {}
    
    public NextPageResponse(String nextPage) {
        this.nextPage = nextPage;
    }
    
    public NextPageResponse(String nextPage, String message) {
        this.nextPage = nextPage;
        this.message = message;
    }
    
    // Getters and setters
    public String getNextPage() { return nextPage; }
    public void setNextPage(String nextPage) { this.nextPage = nextPage; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}