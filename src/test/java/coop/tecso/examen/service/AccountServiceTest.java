package coop.tecso.examen.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.config.exception.DependencyRecordException;
import coop.tecso.examen.dto.AccountDto;
import coop.tecso.examen.model.Account;
import coop.tecso.examen.repository.AccountRepository;
import coop.tecso.examen.repository.MovementRepository;
import coop.tecso.examen.service.impl.AccountServiceImpl;
import coop.tecso.examen.testdata.DozerBeanMapperTest;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTest {

	public static final Long ID_ACCOUNT_DEPENDENCY = 1L;

	private DozerBeanMapper dozerBeanMapper;

	@Before
	public void setUp() {
		dozerBeanMapper = DozerBeanMapperTest.dozerBeanMapper();
	}

	@Test(expected = DependencyRecordException.class)
	public void deleteAccountWithDependencies() {
		AccountRepository accountRepository = mock(AccountRepository.class);
		MovementRepository movementRepository = mock(MovementRepository.class);
		AccountService accountService = new AccountServiceImpl(accountRepository, dozerBeanMapper, movementRepository);
		Account account = new Account();
		account.setId(ID_ACCOUNT_DEPENDENCY);
		when(accountRepository.findById(ID_ACCOUNT_DEPENDENCY)).thenReturn(Optional.of(account));
		when(movementRepository.countByAccount(account)).thenReturn(5L);
		accountService.deleteById(ID_ACCOUNT_DEPENDENCY);
	}
	
	@Test
	public void deleteAccountWithoutDependencies() {
		AccountRepository accountRepository = mock(AccountRepository.class);
		MovementRepository movementRepository = mock(MovementRepository.class);
		AccountService accountService = new AccountServiceImpl(accountRepository, dozerBeanMapper, movementRepository);
		Account account = new Account();
		account.setId(ID_ACCOUNT_DEPENDENCY);
		when(accountRepository.findById(ID_ACCOUNT_DEPENDENCY)).thenReturn(Optional.of(account));
		AccountDto accountDto = accountService.deleteById(ID_ACCOUNT_DEPENDENCY);
		assertEquals(ID_ACCOUNT_DEPENDENCY, accountDto.getId());
	}

}
