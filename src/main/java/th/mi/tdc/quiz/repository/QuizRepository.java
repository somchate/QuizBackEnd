package th.mi.tdc.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import th.mi.tdc.quiz.entity.Nst;
import th.mi.tdc.quiz.entity.Quiz;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query(value = "SELECT * FROM quiz q WHERE q.question_no = :question_id and q.note = :exam_desc", nativeQuery = true)
    Optional<Quiz> findByIdAndNote(String question_id, String exam_desc);

    @Query(value = "SELECT * FROM quiz q WHERE q.note = :exam_desc", nativeQuery = true)
    List<Quiz> findByNote(String exam_desc);

}
