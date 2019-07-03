package coop.tecso.examen.service.rules;

import coop.tecso.examen.config.exception.DataNotValidException;
import coop.tecso.examen.dto.CurrencyCode;

public class MaxAmountCurrencyFactory {

	private MaxAmountCurrencyFactory() {
	}

	public static MaxAmountCurrency create(String currencyCode) {
		if (CurrencyCode.USD.getCode().equals(currencyCode)) {
			return new MaxAmountCurrencyUSD();
		} else if (CurrencyCode.EUR.getCode().equals(currencyCode)) {
			return new MaxAmountCurrencyEUR();
		} else if (CurrencyCode.ARS.getCode().equals(currencyCode)) {
			return new MaxAmountCurrencyARS();
		}
		throw new DataNotValidException("Currency code not valid");
	}

}
