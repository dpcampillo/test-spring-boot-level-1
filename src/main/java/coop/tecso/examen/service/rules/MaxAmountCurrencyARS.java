package coop.tecso.examen.service.rules;

public class MaxAmountCurrencyARS extends MaxAmountCurrency {

	@Override
	public boolean validAmount(Double amount) {
		return amount <= 1000;
	}

}
