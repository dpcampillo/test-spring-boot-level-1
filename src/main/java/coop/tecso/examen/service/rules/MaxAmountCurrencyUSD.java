package coop.tecso.examen.service.rules;

public class MaxAmountCurrencyUSD extends MaxAmountCurrency {

	@Override
	public boolean validAmount(Double amount) {
		return amount <= 300;
	}

}
