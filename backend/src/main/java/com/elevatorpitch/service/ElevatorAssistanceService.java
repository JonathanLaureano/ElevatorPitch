package com.elevatorpitch.service;

import com.elevatorpitch.dto.*;
import com.elevatorpitch.entity.Answer;
import com.elevatorpitch.entity.Question;
import com.elevatorpitch.entity.User;
import com.elevatorpitch.repository.AnswerRepository;
import com.elevatorpitch.repository.QuestionRepository;
import com.elevatorpitch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ElevatorAssistanceService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private AnswerRepository answerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<Question> getAllQuestions() {
        return questionRepository.findAllByOrderByOrderAsc();
    }
    
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }
    
    public User registerUser(UserRegistrationRequest request) {
        String uniqueLink = generateUniqueLink();
        User user = new User(request.getName(), uniqueLink, request.getPhoneNumber());
        return userRepository.save(user);
    }
    
    public NextPageResponse submitAnswer(AnswerRequest request) {
        Optional<User> userOpt = userRepository.findByUniqueLink(request.getUniqueLink());
        Optional<Question> questionOpt = questionRepository.findById(request.getQuestionId());
        
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found with unique link: " + request.getUniqueLink());
        }
        
        if (questionOpt.isEmpty()) {
            throw new RuntimeException("Question not found with id: " + request.getQuestionId());
        }
        
        User user = userOpt.get();
        Question question = questionOpt.get();
        
        // Only record answers from Question 3 forward (order >= 3)
        if (question.getOrder() >= 3) {
            Answer answer = new Answer(user, question, request.getSelectedOption());
            answerRepository.save(answer);
        }
        
        // Determine next page based on question-answer mapping
        String nextPage = question.getAnswerMappings().get(request.getSelectedOption());
        
        if (nextPage == null) {
            nextPage = "error"; // Default fallback
        }
        
        // Special handling for certain pages
        if ("home-popup".equals(nextPage)) {
            return new NextPageResponse("home", "Please return to this site when you are home");
        } else if ("send-tech".equals(nextPage)) {
            // Close the ticket when sending tech
            user.setIsTicketOpen(false);
            userRepository.save(user);
        }
        
        return new NextPageResponse(nextPage);
    }
    
    public List<Answer> getUserAnswers(String uniqueLink) {
        return answerRepository.findByUserUniqueLink(uniqueLink);
    }
    
    public List<AdminTicketView> getAllTickets() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new AdminTicketView(
                    user.getId(),
                    user.getName(),
                    user.getUniqueLink(),
                    user.getIsTicketOpen(),
                    user.getCreatedAt(),
                    user.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }
    
    public Optional<User> getUserByUniqueLink(String uniqueLink) {
        return userRepository.findByUniqueLink(uniqueLink);
    }
    
    private String generateUniqueLink() {
        return "elevator-" + UUID.randomUUID().toString();
    }
}