package coop.tecso.examen.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import coop.tecso.examen.config.exception.RecordNotFoundException;
import coop.tecso.examen.dto.AccountHolderDto;
import coop.tecso.examen.repository.AccountHolderRepository;
import coop.tecso.examen.service.AccountHolderService;

@Service
public class AccountHolderServiceImpl implements AccountHolderService {

	private final AccountHolderRepository accountHolderRepository;
	private final DozerBeanMapper dozerBeanMapper;

	public AccountHolderServiceImpl(AccountHolderRepository accountHolderRepository, DozerBeanMapper dozerBeanMapper) {
		this.accountHolderRepository = accountHolderRepository;
		this.dozerBeanMapper = dozerBeanMapper;
	}

	@Override
	public List<AccountHolderDto> findAll() {
		return accountHolderRepository.findAll().stream()
				.map(mapper -> dozerBeanMapper.map(mapper, AccountHolderDto.class)).collect(Collectors.toList());
	}

	@Override
	public AccountHolderDto deleteById(Long id) {
		return accountHolderRepository.findById(id).map(mapper -> {
			accountHolderRepository.delete(mapper);
			return dozerBeanMapper.map(mapper, AccountHolderDto.class);
		}).orElseThrow(() -> new RecordNotFoundException("Account Holder not found with id " + id));
	}

}
