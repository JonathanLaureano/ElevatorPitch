package com.elevatorpitch.dto;

import java.time.LocalDateTime;

public class AdminTicketView {
    private Long userId;
    private String name;
    private String uniqueLink;
    private Boolean isTicketOpen;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public AdminTicketView() {}
    
    public AdminTicketView(Long userId, String name, String uniqueLink, Boolean isTicketOpen, 
                          LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.name = name;
        this.uniqueLink = uniqueLink;
        this.isTicketOpen = isTicketOpen;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    // Getters and setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getUniqueLink() { return uniqueLink; }
    public void setUniqueLink(String uniqueLink) { this.uniqueLink = uniqueLink; }
    
    public Boolean getIsTicketOpen() { return isTicketOpen; }
    public void setIsTicketOpen(Boolean isTicketOpen) { this.isTicketOpen = isTicketOpen; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}