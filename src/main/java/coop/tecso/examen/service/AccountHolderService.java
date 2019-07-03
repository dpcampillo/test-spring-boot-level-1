package coop.tecso.examen.service;

import java.util.List;

import coop.tecso.examen.dto.AccountHolderDto;

public interface AccountHolderService {

	List<AccountHolderDto> findAll();
	
	AccountHolderDto deleteById(Long id);

}
