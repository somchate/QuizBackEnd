package th.mi.tdc.quiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity(name = "quiz")
public class Quiz {

    @Id
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @Column(name = "choice1", nullable = false)
    private String choice1;

    @Column(name = "choice2", nullable = false)
    private String choice2;

    @Column(name = "choice3", nullable = false)
    private String choice3;

    @Column(name = "choice4", nullable = false)
    private String choice4;

    @JsonIgnore
    @Column(name = "answer", nullable = false)
    private String answer;

    @Column(name = "question_no", nullable = false)
    private String question_no;

    @Column(name = "note", nullable = false)
    private String note;

}
