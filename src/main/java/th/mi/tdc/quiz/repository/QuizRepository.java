package th.mi.tdc.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.mi.tdc.quiz.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> { }
