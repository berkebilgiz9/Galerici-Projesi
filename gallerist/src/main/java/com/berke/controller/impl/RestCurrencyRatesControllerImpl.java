package com.berke.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.berke.controller.IRestCurrencyRatesController;
import com.berke.controller.RestBaseController;
import com.berke.controller.RootEntity;
import com.berke.dto.CurrencyRatesResponse;
import com.berke.service.ICurrencyRatesService;

@RestController
@RequestMapping("/rest/api")
public class RestCurrencyRatesControllerImpl extends RestBaseController implements IRestCurrencyRatesController{
	
	@Autowired
	private ICurrencyRatesService currencyRatesService;
	
	@GetMapping("/currency-rates")
	@Override
	public RootEntity<CurrencyRatesResponse> getCurrencyRates(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
		return ok(currencyRatesService.getCurrencyRates(startDate, endDate));
	}

}
