package th.mi.tdc.quiz.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
	private Long id;
	private String pre_name;
	private String first_name;
	private String last_name;
	private String username;
	private String nst_class;
	private String gender_id;
	private String type = "Bearer";
	private String token;
	private String refreshToken;

	public JwtResponse(String accessToken, String refreshToken, Long id, String username,
					   String first_name, String last_name, String gender_id, String nst_class,
					   String pre_name) {
		this.token = accessToken;
		this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.pre_name = pre_name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender_id = gender_id;
		this.nst_class = nst_class;

	}

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

}
