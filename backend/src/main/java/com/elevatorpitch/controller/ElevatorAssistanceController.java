package com.elevatorpitch.controller;

import com.elevatorpitch.dto.*;
import com.elevatorpitch.entity.Answer;
import com.elevatorpitch.entity.Question;
import com.elevatorpitch.entity.User;
import com.elevatorpitch.service.ElevatorAssistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ElevatorAssistanceController {
    
    @Autowired
    private ElevatorAssistanceService elevatorAssistanceService;
    
    @GetMapping("/questions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = elevatorAssistanceService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }
    
    @GetMapping("/questions/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = elevatorAssistanceService.getQuestionById(id);
        return question.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/users/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            User user = elevatorAssistanceService.registerUser(request);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/answers")
    public ResponseEntity<NextPageResponse> submitAnswer(@RequestBody AnswerRequest request) {
        try {
            NextPageResponse response = elevatorAssistanceService.submitAnswer(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/users/{uniqueLink}/answers")
    public ResponseEntity<List<Answer>> getUserAnswers(@PathVariable String uniqueLink) {
        List<Answer> answers = elevatorAssistanceService.getUserAnswers(uniqueLink);
        return ResponseEntity.ok(answers);
    }
    
    @GetMapping("/users/{uniqueLink}")
    public ResponseEntity<User> getUserByUniqueLink(@PathVariable String uniqueLink) {
        Optional<User> user = elevatorAssistanceService.getUserByUniqueLink(uniqueLink);
        return user.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/admin/tickets")
    public ResponseEntity<List<AdminTicketView>> getAllTickets() {
        List<AdminTicketView> tickets = elevatorAssistanceService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }
}