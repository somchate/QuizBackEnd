package th.mi.tdc.quiz.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import th.mi.tdc.quiz.entity.Quiz;
import th.mi.tdc.quiz.exception.ResourceNotFoundException;
import th.mi.tdc.quiz.repository.QuizRepository;
import th.mi.tdc.quiz.services.QuizService;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }


    @Override
    public Quiz saveQuiz(Quiz quiz) {
        return null;
    }

    @Override
    public List<Quiz> getAllQuiz() {
        return null;
    }

    @Override
    public Page<Quiz> getQuizWithPagination(int offset, int pageSize) {
        return null;
    }

    @Override
    public Page<Quiz> getQuizWithPageFilter(int offset, int pageSize, String question) {
        return null;
    }

    @Override
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public Optional<Quiz> getQuizByIdAndNote(Long id, String exam_desc) {
        return quizRepository.findByIdAndNote(id, exam_desc);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz, Long id) {
        return null;
    }

    @Override
    public void deleteQuiz(Long id) {

    }

    @Override
    public Quiz updateQuiz(Quiz quiz, Long id, String quiz_type) {
        return null;
    }

    @Override
    public Page<Quiz> getQuizWithPageFilter(int offset, int pageSize, String question, String quiz_type) {
        return null;
    }
}