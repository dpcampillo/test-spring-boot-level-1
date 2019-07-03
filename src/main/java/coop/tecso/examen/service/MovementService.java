package coop.tecso.examen.service;

import java.util.List;
import java.util.Optional;

import coop.tecso.examen.dto.MovementDto;

public interface MovementService {

	MovementDto save(MovementDto movementDto);

	List<MovementDto> findAllByAccount(Optional<Long> idAccount);

	MovementDto findById(Long id);

}
