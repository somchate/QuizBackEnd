package th.mi.tdc.quiz.services;

import org.springframework.data.domain.Page;
import th.mi.tdc.quiz.entity.Quiz;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface QuizService {

    Quiz saveQuiz(Quiz quiz);

    List<Quiz> getAllQuiz();

    Page<Quiz> getQuizWithPagination(int offset, int pageSize);

    Page<Quiz> getQuizWithPageFilter(int offset, int pageSize, String question);

    Optional<Quiz> getQuizById(Long id);

    Optional<Quiz> getQuizByIdAndNote (Long id, String exam_desc);

    Quiz updateQuiz(Quiz quiz, Long id);

    void deleteQuiz(Long id);

    Quiz updateQuiz(Quiz quiz, Long id, String quiz_type);

    Page<Quiz> getQuizWithPageFilter(int offset, int pageSize, String question, String quiz_type);

}
