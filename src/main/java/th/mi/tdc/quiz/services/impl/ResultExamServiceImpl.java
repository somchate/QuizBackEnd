package th.mi.tdc.quiz.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.mi.tdc.quiz.exception.ResourceNotFoundException;
import th.mi.tdc.quiz.entity.ResultExam;
import th.mi.tdc.quiz.repository.ResultExamRepository;
import th.mi.tdc.quiz.services.ResultExamService;

import java.util.List;

@Service
public class ResultExamServiceImpl implements ResultExamService {

    @Autowired
    private ResultExamRepository resultExamRepository;

    public ResultExamServiceImpl(ResultExamRepository resultExamRepository) {
        super();
        this.resultExamRepository = resultExamRepository;
    }

    @Override
    public ResultExam saveResultExam(ResultExam resultExam) {
        return resultExamRepository.save(resultExam);
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
    public  List<ResultExam> getByResultExamId(Long id) { return resultExamRepository.findByNstId(id); }


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
