package com.berke.dto;

import java.math.BigDecimal;

import com.berke.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoAccountIU {
	@NotNull
	private String accountNo;
	@NotNull
	private String iban;
	@NotNull
	private BigDecimal amount;
	@NotNull
	private CurrencyType currencyType;
}
