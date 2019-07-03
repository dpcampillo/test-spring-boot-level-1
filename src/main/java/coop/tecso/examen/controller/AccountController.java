package coop.tecso.examen.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.AccountDto;
import coop.tecso.examen.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping
	public AccountDto save(@Valid @RequestBody AccountDto accountDto) {
		return accountService.save(accountDto);
	}

	@GetMapping(value = "/{id}")
	public AccountDto findById(@PathVariable Long id) {
		return accountService.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public AccountDto deleteById(@PathVariable Long id) {
		return accountService.deleteById(id);
	}

	@GetMapping
	public List<AccountDto> findAll() {
		return accountService.findAll();
	}

}
