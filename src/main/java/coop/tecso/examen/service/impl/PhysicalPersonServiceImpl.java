package coop.tecso.examen.service.impl;

import org.dozer.DozerBeanMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import coop.tecso.examen.config.exception.DuplicateRecordException;
import coop.tecso.examen.config.exception.RecordNotFoundException;
import coop.tecso.examen.dto.PhysicalPersonDto;
import coop.tecso.examen.model.PhysicalPerson;
import coop.tecso.examen.repository.PhysicalPersonRepository;
import coop.tecso.examen.service.PhysicalPersonService;

@Service
public class PhysicalPersonServiceImpl implements PhysicalPersonService {

	private final PhysicalPersonRepository physicalPersonRepository;
	private final DozerBeanMapper dozerBeanMapper;

	public PhysicalPersonServiceImpl(PhysicalPersonRepository physicalPersonRepository,
			DozerBeanMapper dozerBeanMapper) {
		this.physicalPersonRepository = physicalPersonRepository;
		this.dozerBeanMapper = dozerBeanMapper;
	}

	@Override
	public PhysicalPersonDto save(PhysicalPersonDto physicalPersonDto) {
		try {
			PhysicalPerson physicalPerson = dozerBeanMapper.map(physicalPersonDto, PhysicalPerson.class);
			physicalPersonRepository.save(physicalPerson);
			return dozerBeanMapper.map(physicalPerson, PhysicalPersonDto.class);
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateRecordException("Duplicate record " + physicalPersonDto.getRut());
		}
	}

	@Override
	public PhysicalPersonDto findById(Long id) {
		return physicalPersonRepository.findById(id).map(mapper -> dozerBeanMapper.map(mapper, PhysicalPersonDto.class))
				.orElseThrow(() -> new RecordNotFoundException("Physical Person not found with id " + id));
	}

	@Override
	public PhysicalPersonDto update(Long id, PhysicalPersonDto physicalPersonDto) {
		try {
			return physicalPersonRepository.findById(id).map(mapper -> {
				mapper.setIdentification(physicalPersonDto.getIdentification());
				mapper.setFirstname(physicalPersonDto.getFirstname());
				mapper.setLastname(physicalPersonDto.getLastname());
				mapper.setRut(physicalPersonDto.getRut());
				physicalPersonRepository.save(mapper);
				return dozerBeanMapper.map(mapper, PhysicalPersonDto.class);
			}).orElseThrow(() -> new RecordNotFoundException("Physical Person not found with id " + id));
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateRecordException("Duplicate record " + physicalPersonDto.getRut());
		}
	}

}
