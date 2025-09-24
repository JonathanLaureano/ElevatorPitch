package com.elevatorpitch.config;

import com.elevatorpitch.entity.Question;
import com.elevatorpitch.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize sample questions if database is empty
        if (questionRepository.count() == 0) {
            Question q1 = new Question(
                "What is your main professional background?",
                Arrays.asList("Technology/Engineering", "Business/Finance", "Healthcare", "Education", "Creative/Arts"),
                1
            );
            
            Question q2 = new Question(
                "What type of opportunity are you seeking?",
                Arrays.asList("Full-time Employment", "Part-time Work", "Freelance/Contract", "Investment/Funding", "Partnership"),
                2
            );
            
            Question q3 = new Question(
                "What is your primary strength?",
                Arrays.asList("Problem Solving", "Leadership", "Technical Skills", "Communication", "Innovation"),
                3
            );
            
            Question q4 = new Question(
                "What makes you unique?",
                Arrays.asList("Diverse Experience", "Specialized Expertise", "Strong Network", "Proven Track Record", "Fresh Perspective"),
                4
            );
            
            Question q5 = new Question(
                "What is your main goal?",
                Arrays.asList("Career Growth", "Making Impact", "Financial Success", "Learning & Development", "Work-Life Balance"),
                5
            );
            
            questionRepository.saveAll(Arrays.asList(q1, q2, q3, q4, q5));
        }
    }
}