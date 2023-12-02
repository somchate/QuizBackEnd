package th.mi.tdc.quiz.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Entity
@Table(	name = "nst",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username")
		})
public class Nst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 10)
	private String nst_id;

	@NotBlank
	@Size(max = 13)
	private String username;

	@NotBlank
	@Size(max = 10)
	private String pre_name;

	@NotBlank
	@Size(max = 120)
	private String first_name;

	@NotBlank
	@Size(max = 120)
	private String last_name;

	@NotBlank
	@Size(max = 1)
	private String gender_id;

	@NotBlank
	private String password;

	private Date dob;

	@NotBlank
	@Size(max = 120)
	private String nst_class;

	@NotBlank
	@Size(max = 120)
	private String edu_year;

	@NotBlank
	@Size(max = 120)
	private String ac_id;

	@NotBlank
	@Size(max = 120)
	private String ac_name;

	private String quiz_date;

	@Size(max = 120)
	private String description;

}

