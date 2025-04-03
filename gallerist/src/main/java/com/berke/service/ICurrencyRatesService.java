package com.berke.service;

import com.berke.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {
	public CurrencyRatesResponse getCurrencyRates(String startDate , String endDate);
}
