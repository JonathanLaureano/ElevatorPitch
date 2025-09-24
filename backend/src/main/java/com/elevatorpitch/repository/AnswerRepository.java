package com.elevatorpitch.repository;

import com.elevatorpitch.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByUserIdOrderByQuestionOrderAsc(Long userId);
    List<Answer> findByUserUniqueLink(String uniqueLink);
}