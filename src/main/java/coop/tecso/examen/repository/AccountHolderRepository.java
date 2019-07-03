package coop.tecso.examen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.examen.model.AccountHolder;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

	List<AccountHolder> findByRut(String rut);

}
