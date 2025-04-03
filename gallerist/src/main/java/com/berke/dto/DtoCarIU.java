package com.berke.dto;

import java.math.BigDecimal;

import com.berke.enums.CarStatusType;
import com.berke.enums.CurrencyType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCarIU {
	@NotNull
	private String plaka;
	@NotNull
	private String model;
	@NotNull
	private String brand;
	@NotNull
	private BigDecimal price;
	
	@NotNull
	private int productionYear;
	@NotNull
	private CurrencyType currencyType;
	@NotNull
	private BigDecimal damagePrice;
	@NotNull
	private CarStatusType carStatusType;
}
