package coop.tecso.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coop.tecso.examen.model.LegalPerson;

public interface LegalPersonRepository extends JpaRepository<LegalPerson, Long>{

}
