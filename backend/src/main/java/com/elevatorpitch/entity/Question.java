package com.elevatorpitch.entity;

import jakarta.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;
    
    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "option_text")
    private List<String> options;
    
    @Column(name = "question_order")
    private Integer order;
    
    @ElementCollection
    @CollectionTable(name = "question_answer_mappings", joinColumns = @JoinColumn(name = "question_id"))
    @MapKeyColumn(name = "answer_option")
    @Column(name = "next_page")
    private Map<String, String> answerMappings;
    
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Answer> answers;
    
    public Question() {}
    
    public Question(String text, List<String> options, Integer order, Map<String, String> answerMappings) {
        this.text = text;
        this.options = options;
        this.order = order;
        this.answerMappings = answerMappings;
    }
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    
    public List<String> getOptions() { return options; }
    public void setOptions(List<String> options) { this.options = options; }
    
    public Integer getOrder() { return order; }
    public void setOrder(Integer order) { this.order = order; }
    
    public Map<String, String> getAnswerMappings() { return answerMappings; }
    public void setAnswerMappings(Map<String, String> answerMappings) { this.answerMappings = answerMappings; }
    
    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }
}