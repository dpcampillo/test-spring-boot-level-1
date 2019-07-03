package coop.tecso.examen.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.config.exception.RecordNotFoundException;
import coop.tecso.examen.model.AccountHolder;
import coop.tecso.examen.repository.AccountHolderRepository;
import coop.tecso.examen.service.impl.AccountHolderServiceImpl;
import coop.tecso.examen.testdata.AccountHolderTestDataBuilder;
import coop.tecso.examen.testdata.DozerBeanMapperTest;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountHolderServiceTest {

	public static final Long ID_ACCOUNT_HOLDER = 2L;

	private DozerBeanMapper dozerBeanMapper;

	@Before
	public void setUp() {
		dozerBeanMapper = DozerBeanMapperTest.dozerBeanMapper();
	}

	@Test
	public void findAllRecord() {
		AccountHolderRepository accountHolderRepository = mock(AccountHolderRepository.class);
		when(accountHolderRepository.findAll()).thenReturn(Arrays.asList(new AccountHolderTestDataBuilder().build()));
		AccountHolderService accountService = new AccountHolderServiceImpl(accountHolderRepository, dozerBeanMapper);
		assertEquals(1, accountService.findAll().size());
	}

	@Test
	public void deleteById() {
		AccountHolder accountHolder = new AccountHolderTestDataBuilder().withId(ID_ACCOUNT_HOLDER).build();
		AccountHolderRepository accountHolderRepository = mock(AccountHolderRepository.class);
		when(accountHolderRepository.findById(ID_ACCOUNT_HOLDER)).thenReturn(Optional.of(accountHolder));
		AccountHolderService accountService = new AccountHolderServiceImpl(accountHolderRepository, dozerBeanMapper);
		assertEquals(accountHolder.getRut(), accountService.deleteById(ID_ACCOUNT_HOLDER).getRut());
	}

	@Test(expected = RecordNotFoundException.class)
	public void deleteByIdNotFound() {
		AccountHolderRepository accountHolderRepository = mock(AccountHolderRepository.class);
		AccountHolderService accountService = new AccountHolderServiceImpl(accountHolderRepository, dozerBeanMapper);
		accountService.deleteById(ID_ACCOUNT_HOLDER);
	}

}
