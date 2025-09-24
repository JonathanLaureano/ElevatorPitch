package com.elevatorpitch.controller;

import com.elevatorpitch.dto.AnswerRequest;
import com.elevatorpitch.dto.OutlineResponse;
import com.elevatorpitch.entity.Question;
import com.elevatorpitch.entity.UserResponse;
import com.elevatorpitch.service.ElevatorPitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ElevatorPitchController {
    
    @Autowired
    private ElevatorPitchService elevatorPitchService;
    
    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = elevatorPitchService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }
    
    @GetMapping("/questions/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = elevatorPitchService.getQuestionById(id);
        return question.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/answers")
    public ResponseEntity<UserResponse> submitAnswer(@RequestBody AnswerRequest request) {
        try {
            UserResponse response = elevatorPitchService.saveAnswer(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/outline/{sessionId}")
    public ResponseEntity<OutlineResponse> generateOutline(@PathVariable String sessionId) {
        OutlineResponse outline = elevatorPitchService.generateOutline(sessionId);
        return ResponseEntity.ok(outline);
    }
    
    @GetMapping("/responses/{sessionId}")
    public ResponseEntity<List<UserResponse>> getSessionResponses(@PathVariable String sessionId) {
        List<UserResponse> responses = elevatorPitchService.getSessionResponses(sessionId);
        return ResponseEntity.ok(responses);
    }
}