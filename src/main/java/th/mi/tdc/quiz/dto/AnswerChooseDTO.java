package th.mi.tdc.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerChooseDTO {

    private String questionID;
    private String answerSelected;


}
