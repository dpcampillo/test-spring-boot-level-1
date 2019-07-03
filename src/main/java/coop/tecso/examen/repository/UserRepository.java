package coop.tecso.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.examen.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);
	
}
