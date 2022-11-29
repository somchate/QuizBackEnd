package th.mi.tdc.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.mi.tdc.quiz.entity.ResultExam;

import java.util.List;

@Repository
public interface ResultExamRepository extends JpaRepository<ResultExam, Long> {
    List<ResultExam> findByNstId(Long id);

}
