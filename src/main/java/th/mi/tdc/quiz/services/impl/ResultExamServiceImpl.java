package th.mi.tdc.quiz.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import th.mi.tdc.quiz.dto.CheckResultExamDTO;
import th.mi.tdc.quiz.exception.ResourceNotFoundException;
import th.mi.tdc.quiz.entity.ResultExam;
import th.mi.tdc.quiz.repository.ResultExamRepository;
import th.mi.tdc.quiz.services.NstService;
import th.mi.tdc.quiz.services.QuizService;
import th.mi.tdc.quiz.services.ResultExamService;

import java.util.List;

@Service
public class ResultExamServiceImpl implements ResultExamService {

    @Autowired
    private final ResultExamRepository resultExamRepository;
    private final NstService nstService;
    private final QuizService quizService;
    private int examPoint;


    public ResultExamServiceImpl(ResultExamRepository resultExamRepository, NstService nstService,
                                 QuizService quizService) {
        super();
        this.resultExamRepository = resultExamRepository;
        this.nstService = nstService;
        this.quizService = quizService;

    }

    @Override
    public ResultExam saveResultExam(ResultExam resultExam) {
        return resultExamRepository.save(resultExam);
    }

    @Override
    public ResponseEntity<?> checkSaveResultExam(Long citizen_id, CheckResultExamDTO checkResultExam) {
        this.examPoint = 0;
        ResultExam resultExamRequest = new ResultExam();

        ResultExam resultExam = (ResultExam) this.nstService.getNstById(citizen_id).map(request -> {
            resultExamRequest.setNst(request);
            resultExamRequest.setExam_date(checkResultExam.getExam_date());
            resultExamRequest.setDescription(checkResultExam.getExam_desc());
            checkResultExam.getAnswerChoose().forEach((key) -> {
                if (key.getQuestionID() != null && key.getAnswerSelected() != null) {

                    this.quizService.getQuizByIdAndNote(key.getQuestionID(), checkResultExam.getExam_desc()).map(quiz -> {
                        if (quiz.getAnswer().equals(key.getAnswerSelected())) {
                            this.examPoint = examPoint + 1;
                        }
                        resultExamRequest.setExam_point(this.examPoint);
                        return resultExamRequest;
                    });

                }
            });
            if (checkResultExam.getExam_point() != null) {
                return this.resultExamRepository.save(resultExamRequest);
            } else {
                return null;
            }

        }).orElseThrow(() -> new ResourceNotFoundException("Not found request with id = " + citizen_id, "", ""));
        if (resultExam == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(checkResultExam, HttpStatus.CREATED);
        }

    }


    @Override
    public List<ResultExam> getAllResultExam() {
        return resultExamRepository.findAll();
    }

    @Override
    public List<ResultExam> getByNstId(Long id) {
        return resultExamRepository.findByNstId(id);
    }


    @Override
    public List<ResultExam> getByResultExamId(Long id) {
        return resultExamRepository.findByNstId(id);
    }

    @Override
    public void deleteResultExam(Long id) {
        resultExamRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("RequestVerify", "ID", id));
        resultExamRepository.deleteById(id);
    }

    @Override
    public ResultExam updateResultExam(ResultExam resultExam, Long id) {
        ResultExam existingResultExam = (ResultExam) this.resultExamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ResultExam", "ID", id));
        existingResultExam.setExam_point(resultExam.getExam_point());
        this.resultExamRepository.save(existingResultExam);
        return existingResultExam;
    }
}
