package coop.tecso.examen.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.service.MovementService;

@RestController
@RequestMapping("/movements")
public class MovementController {

	@Autowired
	private MovementService movementService;

	@PostMapping
	public MovementDto save(@Valid @RequestBody MovementDto movementDto) {
		return movementService.save(movementDto);
	}

	@GetMapping(value = "/{id}")
	public MovementDto findById(@PathVariable Long id) {
		return movementService.findById(id);
	}

	@GetMapping
	public List<MovementDto> findAll(@RequestParam(name = "idAccount") Optional<Long> idAccount) {
		return movementService.findAllByAccount(idAccount);
	}

}
