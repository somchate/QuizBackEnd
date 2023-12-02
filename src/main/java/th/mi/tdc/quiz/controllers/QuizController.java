package th.mi.tdc.quiz.controllers;

import org.springframework.web.bind.annotation.*;
import th.mi.tdc.quiz.entity.Quiz;
import th.mi.tdc.quiz.services.QuizService;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        super();
        this.quizService = quizService;
    }

    // Get by id
    @GetMapping("/v1/quiz/{id}")
    public Optional<Quiz> getQuizById(@PathVariable("id") Long  id) {
        return  quizService.getQuizById(id);
    }

}