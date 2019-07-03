package coop.tecso.examen.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import coop.tecso.examen.config.exception.DataNotValidException;
import coop.tecso.examen.dto.CurrencyCode;
import coop.tecso.examen.service.rules.MovementRule;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovementRuleTest {

	public static final Double AMOUNT_VALID_USD = 150.0;
	public static final Double AMOUNT_VALID_ARS = 800.0;
	public static final Double AMOUNT_VALID_EUR = 120.0;

	public static final Double AMOUNT_INVALID_USD = 600.0;
	public static final Double AMOUNT_INVALID_ARS = 1500.0;
	public static final Double AMOUNT_INVALID_EUR = 3000.0;

	public static final String INVALID_CURRENCY_CODE = "INF";

	@Test
	public void canSaveMovementUSDTest() {
		MovementRule movementRule = new MovementRule();
		assertTrue(movementRule.canSaveMovement(CurrencyCode.USD.getCode(), AMOUNT_VALID_USD));
	}

	@Test
	public void canSaveMovementEURTest() {
		MovementRule movementRule = new MovementRule();
		assertTrue(movementRule.canSaveMovement(CurrencyCode.EUR.getCode(), AMOUNT_VALID_EUR));
	}

	@Test
	public void canSaveMovementARSTest() {
		MovementRule movementRule = new MovementRule();
		assertTrue(movementRule.canSaveMovement(CurrencyCode.ARS.getCode(), AMOUNT_VALID_ARS));
	}

	@Test
	public void canNotSaveMovementUSDTest() {
		MovementRule movementRule = new MovementRule();
		assertFalse(movementRule.canSaveMovement(CurrencyCode.USD.getCode(), AMOUNT_INVALID_USD));
	}

	@Test
	public void canNotSaveMovementEURTest() {
		MovementRule movementRule = new MovementRule();
		assertFalse(movementRule.canSaveMovement(CurrencyCode.EUR.getCode(), AMOUNT_INVALID_EUR));
	}

	@Test
	public void canNotSaveMovementARSTest() {
		MovementRule movementRule = new MovementRule();
		assertFalse(movementRule.canSaveMovement(CurrencyCode.ARS.getCode(), AMOUNT_INVALID_ARS));
	}

	@Test(expected = DataNotValidException.class)
	public void invalidCurrencyCode() {
		MovementRule movementRule = new MovementRule();
		movementRule.canSaveMovement(INVALID_CURRENCY_CODE, AMOUNT_INVALID_ARS);
	}

}
