package th.mi.tdc.quiz.security.services;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import th.mi.tdc.quiz.entity.Nst;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String username;

	private String nst_id;

	private String first_name;

	private String last_name;

	private Date dob;

	@JsonIgnore
	private String password;

	private  String gender_id;

	private String nst_class;

	private  String pre_name;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username, String password, String first_name,
						   String last_name, String gender_id, String nst_class, String pre_name ) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.pre_name = pre_name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender_id = gender_id;
		this.nst_class = nst_class;
	}

	public static UserDetailsImpl build(Nst user) {
		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(),
				user.getPassword(),
				user.getFirst_name(),
				user.getLast_name(),
				user.getGender_id(),
				user.getNst_class(),
				user.getPre_name()
				);
	}


	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}