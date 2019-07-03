package coop.tecso.examen.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import coop.tecso.examen.config.exception.DependencyRecordException;
import coop.tecso.examen.config.exception.RecordNotFoundException;
import coop.tecso.examen.dto.AccountDto;
import coop.tecso.examen.model.Account;
import coop.tecso.examen.repository.AccountRepository;
import coop.tecso.examen.repository.MovementRepository;
import coop.tecso.examen.service.AccountService;
import coop.tecso.examen.service.rules.AccountRule;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final DozerBeanMapper dozerBeanMapper;
	private final MovementRepository movementRepository;

	public AccountServiceImpl(AccountRepository accountRepository, DozerBeanMapper dozerBeanMapper,
			MovementRepository movementRepository) {
		this.accountRepository = accountRepository;
		this.dozerBeanMapper = dozerBeanMapper;
		this.movementRepository = movementRepository;
	}

	@Override
	public AccountDto save(AccountDto accountDto) {
		Account account = dozerBeanMapper.map(accountDto, Account.class);
		accountRepository.save(account);
		return dozerBeanMapper.map(account, AccountDto.class);
	}

	@Override
	public AccountDto findById(Long id) {
		return accountRepository.findById(id).map(mapper -> dozerBeanMapper.map(mapper, AccountDto.class))
				.orElseThrow(() -> new RecordNotFoundException("Account not found with id " + id));
	}

	@Override
	public AccountDto deleteById(Long id) {
		return accountRepository.findById(id).map(mapper -> {
			if (new AccountRule(movementRepository).canDeleteAccount(mapper)) {
				accountRepository.delete(mapper);
				return dozerBeanMapper.map(mapper, AccountDto.class);
			} else {
				throw new DependencyRecordException("Account has movements, can't delete it");
			}
		}).orElseThrow(() -> new RecordNotFoundException("Account not found with id " + id));
	}

	@Override
	public List<AccountDto> findAll() {
		return accountRepository.findAll().stream().map(mapper -> dozerBeanMapper.map(mapper, AccountDto.class))
				.collect(Collectors.toList());
	}

}
