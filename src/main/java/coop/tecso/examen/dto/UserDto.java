package coop.tecso.examen.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@NotBlank
	@Size(max = 100)
	private String username;
	@NotBlank
	@Size(max = 100)
	private String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
