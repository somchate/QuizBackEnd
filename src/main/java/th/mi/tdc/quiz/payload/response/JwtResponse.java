package th.mi.tdc.quiz.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
	private Long id;
	private String pre_name;
	private String first_name;
	private String last_name;
	private String citizen_id;
	private String nst_class;
	private String gender_id;
	private  String ac_name;
	private String quiz_date;
	private String description;
	private String type = "Bearer";
	private String token;
	private String refreshToken;

	public JwtResponse(String accessToken, String refreshToken, Long id, String username,
					   String first_name, String last_name, String gender_id, String nst_class,
					   String pre_name, String ac_name, String quiz_date ,String description) {
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.citizen_id = username;
		this.pre_name = pre_name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender_id = gender_id;
		this.nst_class = nst_class;
		this.ac_name = ac_name;
		this.quiz_date= quiz_date;
		this.description = description;

	}

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

}
