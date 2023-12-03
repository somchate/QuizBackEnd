package th.mi.tdc.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckResultExamDTO {

    private String citizen_id;
    private String exam_point;
    private Date exam_date;
    private String exam_desc;
    private List<AnswerChooseDTO> answerChoose;

}
