package coop.tecso.examen.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import coop.tecso.examen.config.exception.DuplicateRecordException;
import coop.tecso.examen.dto.UserDto;
import coop.tecso.examen.model.User;
import coop.tecso.examen.repository.UserRepository;
import coop.tecso.examen.service.UserService;
import coop.tecso.examen.service.mapper.UserDetailsMapper;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final DozerBeanMapper dozerBeanMapper;
	private final BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, DozerBeanMapper dozerBeanMapper,
			BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.dozerBeanMapper = dozerBeanMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User retrievedUser = userRepository.findByUsername(username);
		if (retrievedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return UserDetailsMapper.build(retrievedUser);
	}

	@Override
	public UserDto register(UserDto userDto) {
		User user = userRepository.findByUsername(userDto.getUsername());
		if (user != null) {
			throw new DuplicateRecordException("Already exists username");
		} else {
			user = new User();
			user.setUsername(userDto.getUsername());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			userRepository.save(user);
			return dozerBeanMapper.map(user, UserDto.class);
		}
	}

}
