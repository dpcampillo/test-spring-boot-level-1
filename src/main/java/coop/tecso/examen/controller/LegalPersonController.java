package coop.tecso.examen.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.AccountHolderType;
import coop.tecso.examen.dto.LegalPersonDto;
import coop.tecso.examen.service.LegalPersonService;

@RestController
@RequestMapping("/legalpersons")
public class LegalPersonController {

	@Autowired
	private LegalPersonService legalPersonService;

	@PostMapping
	public LegalPersonDto save(@Valid @RequestBody LegalPersonDto legalPersonDto) {
		legalPersonDto.setType(AccountHolderType.LEGAL_PERSON.getCode());
		return legalPersonService.save(legalPersonDto);
	}

	@GetMapping(value = "/{id}")
	public LegalPersonDto findById(@PathVariable Long id) {
		return legalPersonService.findById(id);
	}

	@PutMapping(value = "/{id}")
	public LegalPersonDto update(@PathVariable Long id, @Valid @RequestBody LegalPersonDto legalPersonDto) {
		legalPersonDto.setType(AccountHolderType.LEGAL_PERSON.getCode());
		return legalPersonService.update(id, legalPersonDto);
	}

}
