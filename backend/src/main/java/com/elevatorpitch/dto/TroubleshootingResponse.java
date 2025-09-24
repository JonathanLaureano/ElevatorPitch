package com.elevatorpitch.dto;

public class TroubleshootingResponse {
    private String uniqueLink;
    private String summary;
    
    public TroubleshootingResponse() {}
    
    public TroubleshootingResponse(String uniqueLink, String summary) {
        this.uniqueLink = uniqueLink;
        this.summary = summary;
    }
    
    // Getters and setters
    public String getUniqueLink() { return uniqueLink; }
    public void setUniqueLink(String uniqueLink) { this.uniqueLink = uniqueLink; }
    
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}