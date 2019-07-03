package coop.tecso.examen.service;

import java.util.List;

import coop.tecso.examen.dto.AccountDto;

public interface AccountService {

	AccountDto save(AccountDto accountDto);

	AccountDto findById(Long id);

	AccountDto deleteById(Long id);

	List<AccountDto> findAll();

}
