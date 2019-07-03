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
import coop.tecso.examen.dto.PhysicalPersonDto;
import coop.tecso.examen.service.PhysicalPersonService;

@RestController
@RequestMapping("/physicalpersons")
public class PhysicalPersonController {

	@Autowired
	private PhysicalPersonService physicalPersonService;

	@PostMapping
	public PhysicalPersonDto save(@Valid @RequestBody PhysicalPersonDto physicalPersonDto) {
		physicalPersonDto.setType(AccountHolderType.PHYSICAL_PERSON.getCode());
		return physicalPersonService.save(physicalPersonDto);
	}
	
	@GetMapping(value = "/{id}")
	public PhysicalPersonDto findById(@PathVariable Long id) {
		return physicalPersonService.findById(id);
	}

	@PutMapping(value = "/{id}")
	public PhysicalPersonDto update(@PathVariable Long id, @Valid @RequestBody PhysicalPersonDto physicalPersonDto) {
		physicalPersonDto.setType(AccountHolderType.PHYSICAL_PERSON.getCode());
		return physicalPersonService.update(id, physicalPersonDto);
	}

}
