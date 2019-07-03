package coop.tecso.examen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.AccountHolderDto;
import coop.tecso.examen.service.AccountHolderService;

@RestController
@RequestMapping("/accountholders")
public class AccountHolderController {

	@Autowired
	private AccountHolderService accountHolderService;

	@GetMapping
	public List<AccountHolderDto> findAllAccountHolders() {
		return accountHolderService.findAll();
	}

	@DeleteMapping(value = "/{id}")
	public AccountHolderDto deleteById(@PathVariable Long id) {
		return accountHolderService.deleteById(id);
	}

}
