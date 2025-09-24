package com.elevatorpitch.repository;

import com.elevatorpitch.entity.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserResponseRepository extends JpaRepository<UserResponse, Long> {
    List<UserResponse> findBySessionIdOrderByQuestionOrderAsc(String sessionId);
}