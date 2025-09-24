package com.elevatorpitch.dto;

public class OutlineResponse {
    private String sessionId;
    private String outline;
    
    public OutlineResponse() {}
    
    public OutlineResponse(String sessionId, String outline) {
        this.sessionId = sessionId;
        this.outline = outline;
    }
    
    // Getters and setters
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public String getOutline() { return outline; }
    public void setOutline(String outline) { this.outline = outline; }
}