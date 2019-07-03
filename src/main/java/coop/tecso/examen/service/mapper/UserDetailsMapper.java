package coop.tecso.examen.service.mapper;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import coop.tecso.examen.model.User;

public class UserDetailsMapper {
	public static UserDetails build(User user) {
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthorities(user));
	}

	private static Set<? extends GrantedAuthority> getAuthorities(User retrievedUser) {
		Set<SimpleGrantedAuthority> roles = new HashSet<>();
		roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		return roles;
	}
}
