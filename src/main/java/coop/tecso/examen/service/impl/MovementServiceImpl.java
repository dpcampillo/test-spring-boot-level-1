package coop.tecso.examen.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import coop.tecso.examen.config.exception.DataNotValidException;
import coop.tecso.examen.config.exception.RecordNotFoundException;
import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.dto.MovementType;
import coop.tecso.examen.model.Account;
import coop.tecso.examen.model.Movement;
import coop.tecso.examen.repository.AccountRepository;
import coop.tecso.examen.repository.MovementRepository;
import coop.tecso.examen.service.MovementService;
import coop.tecso.examen.service.rules.MovementRule;

@Service
public class MovementServiceImpl implements MovementService {

	private final AccountRepository accountRepository;
	private final DozerBeanMapper dozerBeanMapper;
	private final MovementRepository movementRepository;

	public MovementServiceImpl(AccountRepository accountRepository, DozerBeanMapper dozerBeanMapper,
			MovementRepository movementRepository) {
		this.accountRepository = accountRepository;
		this.dozerBeanMapper = dozerBeanMapper;
		this.movementRepository = movementRepository;
	}

	@Override
	@Transactional
	public synchronized MovementDto save(MovementDto movementDto) {
		Optional<Account> optAcount = accountRepository.findById(movementDto.getAccount().getId());
		if (optAcount.isPresent()) {
			Account account = optAcount.get();
			if (new MovementRule().canSaveMovement(account.getCurrency(), movementDto.getAmount())) {
				Movement movement = dozerBeanMapper.map(movementDto, Movement.class);
				movement.setEventdate(LocalDateTime.now());
				movementRepository.save(movement);
				updateBalance(account, movement);
				return dozerBeanMapper.map(movement, MovementDto.class);
			} else {
				throw new DataNotValidException("Amount exceeds the limits allowed");
			}
		} else {
			throw new RecordNotFoundException("Account not found");
		}
	}

	@Override
	public List<MovementDto> findAllByAccount(Optional<Long> idAccount) {
		if (idAccount.isPresent()) {
			Account account = new Account();
			account.setId(idAccount.get());
			return movementRepository.findByAccount(account, Sort.by(Direction.DESC, "eventdate")).stream()
					.map(mapper -> dozerBeanMapper.map(mapper, MovementDto.class)).collect(Collectors.toList());
		} else {
			return movementRepository.findAll(Sort.by(Direction.DESC, "eventdate")).stream()
					.map(mapper -> dozerBeanMapper.map(mapper, MovementDto.class)).collect(Collectors.toList());
		}

	}

	private void updateBalance(Account account, Movement movement) {
		if (movement.getTypemov().equals(MovementType.DEBIT.getCode())) {
			account.setBalance(account.getBalance() - movement.getAmount());
		} else if (movement.getTypemov().equals(MovementType.CREDIT.getCode())) {
			account.setBalance(account.getBalance() + movement.getAmount());
		} else {
			throw new DataNotValidException("Movement Type Not Valid");
		}
		accountRepository.save(account);
	}

	@Override
	public MovementDto findById(Long id) {
		return movementRepository.findById(id).map(mapper -> dozerBeanMapper.map(mapper, MovementDto.class))
				.orElseThrow(() -> new RecordNotFoundException("Movement not found with id " + id));
	}

}
