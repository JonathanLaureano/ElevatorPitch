package com.elevatorpitch.service;

import com.elevatorpitch.dto.AnswerRequest;
import com.elevatorpitch.dto.OutlineResponse;
import com.elevatorpitch.entity.Question;
import com.elevatorpitch.entity.UserResponse;
import com.elevatorpitch.repository.QuestionRepository;
import com.elevatorpitch.repository.UserResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ElevatorPitchService {
    
    @Autowired
    private QuestionRepository questionRepository;
    
    @Autowired
    private UserResponseRepository userResponseRepository;
    
    public List<Question> getAllQuestions() {
        return questionRepository.findAllByOrderByOrderAsc();
    }
    
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }
    
    public UserResponse saveAnswer(AnswerRequest request) {
        Optional<Question> question = questionRepository.findById(request.getQuestionId());
        if (question.isPresent()) {
            UserResponse response = new UserResponse(
                request.getSessionId(),
                question.get(),
                request.getSelectedOption()
            );
            return userResponseRepository.save(response);
        }
        throw new RuntimeException("Question not found with id: " + request.getQuestionId());
    }
    
    public OutlineResponse generateOutline(String sessionId) {
        List<UserResponse> responses = userResponseRepository.findBySessionIdOrderByQuestionOrderAsc(sessionId);
        StringBuilder outline = new StringBuilder();
        outline.append("Elevator Pitch Outline for Session: ").append(sessionId).append("\n\n");
        
        for (UserResponse response : responses) {
            outline.append("Q: ").append(response.getQuestion().getText()).append("\n");
            outline.append("A: ").append(response.getSelectedOption()).append("\n\n");
        }
        
        // Generate a simple pitch based on answers
        outline.append("Generated Elevator Pitch:\n");
        outline.append("Based on your responses, here's a structured outline for your elevator pitch.\n");
        
        return new OutlineResponse(sessionId, outline.toString());
    }
    
    public List<UserResponse> getSessionResponses(String sessionId) {
        return userResponseRepository.findBySessionIdOrderByQuestionOrderAsc(sessionId);
    }
}