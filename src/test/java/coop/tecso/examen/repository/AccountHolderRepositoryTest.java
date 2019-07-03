package coop.tecso.examen.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.model.AccountHolder;
import coop.tecso.examen.testdata.AccountHolderTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountHolderRepositoryTest {

	private static String RUT_TEST = "1051445";

	@Autowired
	private AccountHolderRepository accountHolderRepository;

	@Before
	public void setUp() {
		accountHolderRepository.save(new AccountHolderTestDataBuilder().withRut(RUT_TEST).build());
	}

	@Test
	public void findAllAccountHolderBeforeInsertRecord() {
		List<AccountHolder> result = accountHolderRepository.findAll();
		assertEquals(1, result.size());
	}

	@Test
	public void findAccountHolderByRut() {
		Optional<AccountHolder> titular = accountHolderRepository.findByRut(RUT_TEST).stream().findFirst();
		assertTrue(titular.isPresent());
		assertEquals(RUT_TEST, titular.get().getRut());
	}

}
