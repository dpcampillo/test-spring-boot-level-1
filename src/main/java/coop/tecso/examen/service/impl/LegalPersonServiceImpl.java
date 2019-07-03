package coop.tecso.examen.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import coop.tecso.examen.config.exception.DuplicateRecordException;
import coop.tecso.examen.config.exception.RecordNotFoundException;
import coop.tecso.examen.dto.LegalPersonDto;
import coop.tecso.examen.model.LegalPerson;
import coop.tecso.examen.repository.LegalPersonRepository;
import coop.tecso.examen.service.LegalPersonService;

@Service
public class LegalPersonServiceImpl implements LegalPersonService {

	private final LegalPersonRepository legalPersonRepository;
	private final DozerBeanMapper dozerBeanMapper;

	public LegalPersonServiceImpl(LegalPersonRepository legalPersonRepository, DozerBeanMapper dozerBeanMapper) {
		this.legalPersonRepository = legalPersonRepository;
		this.dozerBeanMapper = dozerBeanMapper;
	}

	@Override
	public LegalPersonDto save(LegalPersonDto legalPersonDto) {
		try {
			LegalPerson legalPerson = dozerBeanMapper.map(legalPersonDto, LegalPerson.class);
			legalPersonRepository.save(legalPerson);
			return dozerBeanMapper.map(legalPerson, LegalPersonDto.class);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateRecordException("Duplicate record " + legalPersonDto.getRut());
		}
	}

	@Override
	public LegalPersonDto findById(Long id) {
		return legalPersonRepository.findById(id).map(mapper -> dozerBeanMapper.map(mapper, LegalPersonDto.class))
				.orElseThrow(() -> new RecordNotFoundException("Legal Person not found with id " + id));
	}

	@Override
	public LegalPersonDto update(Long id, LegalPersonDto legalPersonDto) {
		try {
			return legalPersonRepository.findById(id).map(mapper -> {
				mapper.setRut(legalPersonDto.getRut());
				mapper.setBusinessname(legalPersonDto.getBusinessname());
				mapper.setFounded(legalPersonDto.getFounded());
				legalPersonRepository.save(mapper);
				return legalPersonDto;
			}).orElseThrow(() -> new RecordNotFoundException("Legal Person not found with id " + id));
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateRecordException("Duplicate record " + legalPersonDto.getRut());
		}
	}

}
