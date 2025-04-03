package com.berke.service.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.berke.dto.CurrencyRatesResponse;
import com.berke.exception.BaseException;
import com.berke.exception.ErrorMessage;
import com.berke.exception.MessageType;
import com.berke.service.ICurrencyRatesService;
@Service
public class CurrencyRatesServiceImpl implements ICurrencyRatesService{
	
	
	@Override
	public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate) {
		String rootURL="https://evds2.tcmb.gov.tr/service/evds/";
		String series="TP.DK.USD.A";
		String type="json";
		String endPoint = rootURL+"series="+series+"&startDate="+startDate+"&endDate="+endDate+"&type="+type;

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("key", "TG4dUwTRWt");
		
		HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<CurrencyRatesResponse> response = restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<CurrencyRatesResponse>() {
			});
			if(response.getStatusCode().is2xxSuccessful()) {
				return response.getBody();
			}	
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.CURRENCY_RATES_IS_OCCURED, e.getMessage()));
		}
		return null;	
	}
}
