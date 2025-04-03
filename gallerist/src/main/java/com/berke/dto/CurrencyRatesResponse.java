package com.berke.dto;

import java.util.List;

import lombok.Data;

@Data
public class CurrencyRatesResponse {
	private int totalCount;
	private List<CurrencyRatesItems> items;
}
