package coop.tecso.examen.service.rules;

public class MovementRule {

	public boolean canSaveMovement(String currencyCode, Double amount) {
		return MaxAmountCurrencyFactory.create(currencyCode).validAmount(amount);
	}

}
