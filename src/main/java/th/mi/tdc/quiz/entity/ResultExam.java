package th.mi.tdc.quiz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "result_exam")
public class ResultExam extends BaseEntity {

    @Column(nullable = false)
    private Integer exam_point;

    @Column(nullable = false)
    private Date exam_date;

    @Column(length = 200)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nst_cid", nullable = false)
    @JsonIgnore
    private Nst nst;

}
