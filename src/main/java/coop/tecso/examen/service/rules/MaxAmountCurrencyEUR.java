package coop.tecso.examen.service.rules;

public class MaxAmountCurrencyEUR extends MaxAmountCurrency {

	@Override
	public boolean validAmount(Double amount) {
		return amount <= 150;
	}

}
