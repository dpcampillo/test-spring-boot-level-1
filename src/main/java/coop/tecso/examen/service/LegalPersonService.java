package coop.tecso.examen.service;

import coop.tecso.examen.dto.LegalPersonDto;

public interface LegalPersonService {
	
	LegalPersonDto save(LegalPersonDto legalPersonDto);

	LegalPersonDto findById(Long id);
	
	LegalPersonDto update(Long id, LegalPersonDto legalPersonDto);
	
}
