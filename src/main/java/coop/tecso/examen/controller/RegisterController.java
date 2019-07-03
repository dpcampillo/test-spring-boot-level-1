package coop.tecso.examen.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.UserDto;
import coop.tecso.examen.service.UserService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;

	@PostMapping
	public UserDto register(@Valid @RequestBody UserDto userDto) {
		return userService.register(userDto);
	}

}
