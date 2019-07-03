package coop.tecso.examen.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import coop.tecso.examen.dto.UserDto;

public interface UserService extends UserDetailsService {

	UserDto register(UserDto userDto);

}
