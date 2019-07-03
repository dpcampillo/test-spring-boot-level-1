package coop.tecso.examen.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.config.exception.DataNotValidException;
import coop.tecso.examen.dto.AccountDto;
import coop.tecso.examen.dto.CurrencyCode;
import coop.tecso.examen.dto.MovementDto;
import coop.tecso.examen.dto.MovementType;
import coop.tecso.examen.model.Account;
import coop.tecso.examen.model.LegalPerson;
import coop.tecso.examen.repository.AccountRepository;
import coop.tecso.examen.repository.LegalPersonRepository;
import coop.tecso.examen.repository.MovementRepository;
import coop.tecso.examen.service.impl.MovementServiceImpl;
import coop.tecso.examen.testdata.AccountTestDataBuilder;
import coop.tecso.examen.testdata.DozerBeanMapperTest;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovementServiceTest {

	public static final Double ACTUAL_BALANCE = 4000.0;

	public static final Double CREDIT_BALANCE = 200.0;
	public static final Double DEBIT_BALANCE = 200.0;

	private DozerBeanMapper dozerBeanMapper;

	@Autowired
	private LegalPersonRepository legalPersonRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private MovementRepository movementRepository;

	private LegalPerson legalPerson;
	private Account accountDebit;
	private Account accountCredit;

	@Before
	public void setUp() {
		dozerBeanMapper = DozerBeanMapperTest.dozerBeanMapper();

		legalPerson = new LegalPerson();
		legalPerson.setRut("100000000");
		legalPerson.setBusinessname("XXXXXX TEST");
		legalPerson.setFounded(1990);
		legalPersonRepository.save(legalPerson);
		accountCredit = new AccountTestDataBuilder().buildModel();
		accountCredit.setAccountHolder(legalPerson);
		accountCredit.setBalance(ACTUAL_BALANCE);
		accountRepository.save(accountCredit);
		
		accountDebit = new AccountTestDataBuilder().buildModel();
		accountDebit.setAccountHolder(legalPerson);
		accountDebit.setBalance(ACTUAL_BALANCE);
		accountRepository.save(accountDebit);
	}

	@Test(expected = DataNotValidException.class)
	public void saveMovement() {
		AccountDto account = new AccountDto();
		account.setId(1L);
		account.setCurrency(CurrencyCode.USD.getCode());
		MovementDto movementDto = new MovementDto();
		movementDto.setAccount(account);
		movementDto.setAmount(1000.0);
		AccountRepository accountRepository = mock(AccountRepository.class);
		when(accountRepository.findById(1L)).thenReturn(Optional.of(dozerBeanMapper.map(account, Account.class)));
		MovementRepository movementRepository = mock(MovementRepository.class);
		MovementService movementService = new MovementServiceImpl(accountRepository, dozerBeanMapper,
				movementRepository);
		movementService.save(movementDto);
	}

	@Test
	public void checkBalanceAfterSave() {
		MovementService movementService = new MovementServiceImpl(accountRepository, dozerBeanMapper,
				movementRepository);
		MovementDto movementDto = new MovementDto();
		movementDto.setAccount(dozerBeanMapper.map(accountCredit, AccountDto.class));
		movementDto.setAmount(CREDIT_BALANCE);
		movementDto.setDescription("Test of movements");
		movementDto.setTypemov(MovementType.CREDIT.getCode());
		movementService.save(movementDto);

		Optional<Account> optAccount = accountRepository.findById(accountCredit.getId());
		if (optAccount.isPresent()) {
			assertEquals(ACTUAL_BALANCE + CREDIT_BALANCE, optAccount.get().getBalance(), 0);
		} else {
			fail();
		}

	}
	
	@Test
	public void checkBalanceAfterSaveDebit() {
		MovementService movementService = new MovementServiceImpl(accountRepository, dozerBeanMapper,
				movementRepository);
		MovementDto movementDto = new MovementDto();
		movementDto.setAccount(dozerBeanMapper.map(accountDebit, AccountDto.class));
		movementDto.setAmount(DEBIT_BALANCE);
		movementDto.setDescription("Test of movements");
		movementDto.setTypemov(MovementType.DEBIT.getCode());
		movementService.save(movementDto);

		Optional<Account> optAccount = accountRepository.findById(accountDebit.getId());
		if (optAccount.isPresent()) {
			assertEquals(ACTUAL_BALANCE - DEBIT_BALANCE, optAccount.get().getBalance(), 0);
		} else {
			fail();
		}

	}

}
