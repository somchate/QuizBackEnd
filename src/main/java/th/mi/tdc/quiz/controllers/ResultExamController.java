package th.mi.tdc.quiz.controllers;

import java.util.List;
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
import th.mi.tdc.quiz.entity.ResultExam;
import th.mi.tdc.quiz.exception.ResourceNotFoundException;
import th.mi.tdc.quiz.services.NstService;
import th.mi.tdc.quiz.services.ResultExamService;

@CrossOrigin(origins = {"*"}, maxAge = 3600L)
@RestController
@RequestMapping({"/api"})
public class ResultExamController {

    @Autowired
    private ResultExamService resultExamService;

    @Autowired
    private NstService nstService;

    public ResultExamController(ResultExamService resultExamService, NstService nstService) {
        this.resultExamService = resultExamService;
        this.nstService = nstService;
    }

    @PostMapping({"/v1/nsts/{citizen_id}/results"})
    public ResponseEntity<ResultExam> createNstPoint(@PathVariable("citizen_id") Long citizen_id, @RequestBody ResultExam resultExamRequest) {
        ResultExam resultExam = (ResultExam)this.nstService.getNstById(citizen_id).map(request -> {
            resultExamRequest.setNst(request);
            return this.resultExamService.saveResultExam(resultExamRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found request with id = " + citizen_id, "", ""));
        return new ResponseEntity<>(resultExam, HttpStatus.CREATED);
    }

    @PutMapping({"/v1/results/{id}"})
    public ResponseEntity<ResultExam> updateNst(@PathVariable("id") Long id, @RequestBody ResultExam resultExamRequest) {
        return new ResponseEntity<>(this.resultExamService.updateResultExam(resultExamRequest, id), HttpStatus.OK);
    }

    @GetMapping({"/v1/nsts/{citizen_id}/results"})
    public ResponseEntity<List<ResultExam>> getResultsByRequestId(@PathVariable("citizen_id") Long requestId) {
        if (this.resultExamService.getByNstId(requestId) == null)
            throw new ResourceNotFoundException("Not found Tutorial with id = " + requestId, "", "");
        List<ResultExam> results = this.resultExamService.getByNstId(requestId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping({"/v1/"})
    public List<ResultExam> getAllResultVerify() {
        return this.resultExamService.getAllResultExam();
    }

    @DeleteMapping({"/v1/results/{id}"})
    public ResponseEntity<String> deleteResultVerify(@PathVariable("id") Long id) {
        this.resultExamService.deleteResultExam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
