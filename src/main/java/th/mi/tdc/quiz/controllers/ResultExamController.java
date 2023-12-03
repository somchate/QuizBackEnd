package th.mi.tdc.quiz.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.mi.tdc.quiz.dto.CheckResultExamDTO;
import th.mi.tdc.quiz.entity.ResultExam;
import th.mi.tdc.quiz.exception.ResourceNotFoundException;
import th.mi.tdc.quiz.services.NstService;
import th.mi.tdc.quiz.services.QuizService;
import th.mi.tdc.quiz.services.ResultExamService;

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
@RequestMapping({"/api"})
public class ResultExamController {

    @Autowired
    private final ResultExamService resultExamService;

    @Autowired
    private final QuizService quizService;

    @Autowired
    private final NstService nstService;
    private int examPoint;

    public ResultExamController(ResultExamService resultExamService, NstService nstService,
                                QuizService quizService) {
        this.resultExamService = resultExamService;
        this.nstService = nstService;
        this.quizService = quizService;
    }

    @CrossOrigin(origins = "https://gdcc.tdc.mi.th", maxAge = 3600)
    @PostMapping({"/v1/nsts/{citizen_id}/resultexam"})
    public ResponseEntity<ResultExam> createNstPoint(@PathVariable("citizen_id") Long citizen_id,
                                                     @RequestBody ResultExam resultExamRequest) {
        ResultExam resultExam = (ResultExam) this.nstService.getNstById(citizen_id).map(request -> {
            resultExamRequest.setNst(request);
            return this.resultExamService.saveResultExam(resultExamRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found request with id = " + citizen_id, "", ""));
        return new ResponseEntity<>(resultExam, HttpStatus.CREATED);
    }

    @PostMapping({"/v1/nsts/checksaveresultexam/{citizen_id}"})
    public ResponseEntity<?> checkSaveResultExam(@PathVariable("citizen_id") Long citizen_id,
                                                 @RequestBody CheckResultExamDTO checkResultExam) {
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
                }});
                return this.resultExamService.saveResultExam(resultExamRequest);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found request with id = " + citizen_id, "", ""));
            return new ResponseEntity<>(checkResultExam, HttpStatus.CREATED);
        }

        @PutMapping({"/v1/results/{id}"})
        public ResponseEntity<ResultExam> updateNst (@PathVariable("id") Long id, @RequestBody ResultExam
        resultExamRequest){
            return new ResponseEntity<>(this.resultExamService.updateResultExam(resultExamRequest, id), HttpStatus.OK);
        }

        @GetMapping({"/v1/nsts/{citizen_id}/results"})
        public ResponseEntity<List<ResultExam>> getResultsByRequestId (@PathVariable("citizen_id") Long requestId){
            if (this.resultExamService.getByNstId(requestId) == null)
                throw new ResourceNotFoundException("Not found Tutorial with id = " + requestId, "", "");
            List<ResultExam> results = this.resultExamService.getByNstId(requestId);
            return new ResponseEntity<>(results, HttpStatus.OK);
        }

        @GetMapping({"/v1/"})
        public List<ResultExam> getAllResultVerify () {
            return this.resultExamService.getAllResultExam();
        }

        @DeleteMapping({"/v1/results/{id}"})
        public ResponseEntity<String> deleteResultVerify (@PathVariable("id") Long id){
            this.resultExamService.deleteResultExam(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
