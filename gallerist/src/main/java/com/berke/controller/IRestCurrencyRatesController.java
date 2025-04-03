package com.berke.controller;

import com.berke.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {
	public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}
