package com.elevatorpitch.dto;

public class AnswerRequest {
    private String sessionId;
    private Long questionId;
    private String selectedOption;
    
    public AnswerRequest() {}
    
    public AnswerRequest(String sessionId, Long questionId, String selectedOption) {
        this.sessionId = sessionId;
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }
    
    // Getters and setters
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }
    
    public String getSelectedOption() { return selectedOption; }
    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }
}