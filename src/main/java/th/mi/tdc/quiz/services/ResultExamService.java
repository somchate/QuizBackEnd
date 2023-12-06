package th.mi.tdc.quiz.services;

import org.springframework.http.ResponseEntity;
import th.mi.tdc.quiz.dto.CheckResultExamDTO;
import th.mi.tdc.quiz.entity.ResultExam;

import java.util.List;

public interface ResultExamService {

    ResultExam saveResultExam(ResultExam resultExam);

    ResponseEntity<?> checkSaveResultExam(Long citizen_id, CheckResultExamDTO checkResultExam);

    List<ResultExam> getAllResultExam();

    List<ResultExam> getByNstId(Long id);

    ResultExam updateResultExam(ResultExam resultExam, Long id);

    List<ResultExam> getByResultExamId(Long id);

    void deleteResultExam(Long id);

}