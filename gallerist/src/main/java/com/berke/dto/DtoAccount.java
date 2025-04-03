package com.berke.dto;

import java.math.BigDecimal;

import com.berke.enums.CurrencyType;

import lombok.Data;

@Data
public class DtoAccount extends DtoBase{
	private String accountNo;
	private String iban;
	private BigDecimal amount;
	private CurrencyType currencyType;
}
