package th.mi.tdc.quiz.services;

import th.mi.tdc.quiz.entity.ResultExam;

import java.util.List;

public interface ResultExamService {

    ResultExam saveResultExam(ResultExam resultExam);
    List<ResultExam> getAllResultExam();
    List<ResultExam> getByNstId(Long id);
    ResultExam updateResultExam(ResultExam resultExam, Long id);


    List<ResultExam> getByResultExamId(Long id);

    void deleteResultExam(Long id);

}