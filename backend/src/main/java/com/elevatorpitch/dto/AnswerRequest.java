package com.elevatorpitch.dto;

public class AnswerRequest {
    private String uniqueLink;
    private Long questionId;
    private String selectedOption;
    
    public AnswerRequest() {}
    
    public AnswerRequest(String uniqueLink, Long questionId, String selectedOption) {
        this.uniqueLink = uniqueLink;
        this.questionId = questionId;
        this.selectedOption = selectedOption;
    }
    
    // Getters and setters
    public String getUniqueLink() { return uniqueLink; }
    public void setUniqueLink(String uniqueLink) { this.uniqueLink = uniqueLink; }
    
    public Long getQuestionId() { return questionId; }
    public void setQuestionId(Long questionId) { this.questionId = questionId; }
    
    public String getSelectedOption() { return selectedOption; }
    public void setSelectedOption(String selectedOption) { this.selectedOption = selectedOption; }
}