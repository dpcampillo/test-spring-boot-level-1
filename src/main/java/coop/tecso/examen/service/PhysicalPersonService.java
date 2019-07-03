package coop.tecso.examen.service;

import coop.tecso.examen.dto.PhysicalPersonDto;

public interface PhysicalPersonService {
	
	PhysicalPersonDto save(PhysicalPersonDto physicalPersonDto);

	PhysicalPersonDto findById(Long id);
	
	PhysicalPersonDto update(Long id, PhysicalPersonDto physicalPersonDto);
	
}
