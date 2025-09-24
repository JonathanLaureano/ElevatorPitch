package com.elevatorpitch.config;

import com.elevatorpitch.entity.Question;
import com.elevatorpitch.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DataLoader implements CommandLineRunner {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Only load data if questions don't already exist
        if (questionRepository.exists()) {
            return;
        }
        
        // Question 1
        Map<String, String> question1Mappings = new HashMap<>();
        question1Mappings.put("Yes", "question/2");
        question1Mappings.put("No", "send-tech");
        
        Question question1 = new Question(
            "Are you or someone in your household comfortable and able to troubleshoot?",
            Arrays.asList("Yes", "No"),
            1,
            question1Mappings
        );
        
        // Question 2
        Map<String, String> question2Mappings = new HashMap<>();
        question2Mappings.put("Yes", "question/3");
        question2Mappings.put("No", "home-popup");
        
        Question question2 = new Question(
            "Are you currently at the property with the elevator?",
            Arrays.asList("Yes", "No"),
            2,
            question2Mappings
        );
        
        // Question 3
        Map<String, String> question3Mappings = new HashMap<>();
        question3Mappings.put("Traction", "question/4");
        question3Mappings.put("Hydraulic", "question/4");
        question3Mappings.put("Unsure", "elevator-type-help");
        
        Question question3 = new Question(
            "What type of elevator do you have?",
            Arrays.asList("Traction", "Hydraulic", "Unsure"),
            3,
            question3Mappings
        );
        
        // Question 4 - Leading to Allen Bradley page
        Map<String, String> question4Mappings = new HashMap<>();
        question4Mappings.put("Allen Bradley", "allen-bradley-elevator");
        question4Mappings.put("Other manufacturer", "send-tech");
        question4Mappings.put("I don't know", "send-tech");
        
        Question question4 = new Question(
            "What elevator control system manufacturer do you have?",
            Arrays.asList("Allen Bradley", "Other manufacturer", "I don't know"),
            4,
            question4Mappings
        );
        
        // Save all questions
        questionRepository.saveAll(Arrays.asList(question1, question2, question3, question4));
        
        System.out.println("Data loader completed: initialized " + questionRepository.count() + " questions");
    }
}