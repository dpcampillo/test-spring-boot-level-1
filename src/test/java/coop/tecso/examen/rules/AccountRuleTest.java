package coop.tecso.examen.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.model.Account;
import coop.tecso.examen.repository.MovementRepository;
import coop.tecso.examen.service.rules.AccountRule;
import coop.tecso.examen.testdata.AccountTestDataBuilder;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRuleTest {

	public static final Long ID_ACCOUNT = 1L;

	@Test
	public void canDeleteAccountTest() {
		Account account = new AccountTestDataBuilder().withId(ID_ACCOUNT).buildModel();
		MovementRepository movementRepository = mock(MovementRepository.class);
		when(movementRepository.countByAccount(account)).thenReturn(0l);
		AccountRule accountRule = new AccountRule(movementRepository);
		assertTrue(accountRule.canDeleteAccount(account));

	}

	@Test
	public void canNotDeleteAccountTest() {
		Account account = new AccountTestDataBuilder().withId(ID_ACCOUNT).buildModel();
		MovementRepository movementRepository = mock(MovementRepository.class);
		when(movementRepository.countByAccount(account)).thenReturn(1l);
		AccountRule accountRule = new AccountRule(movementRepository);
		assertFalse(accountRule.canDeleteAccount(account));

	}

}
