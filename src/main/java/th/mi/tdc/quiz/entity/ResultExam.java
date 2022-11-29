package th.mi.tdc.quiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "result_exam")
public class ResultExam extends BaseEntity {

    @Column(nullable = false)
    private Integer exam_point;

    @Column(nullable = false)
    private Date exam_date;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nst_id", nullable = false)
    @JsonIgnore
    private Nst nst;

}