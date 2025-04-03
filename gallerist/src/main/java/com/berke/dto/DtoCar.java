package com.berke.dto;

import java.math.BigDecimal;

import com.berke.enums.CarStatusType;
import com.berke.enums.CurrencyType;

import lombok.Data;

@Data
public class DtoCar extends DtoBase{
	private String plaka;
	private String model;
	private BigDecimal price;
	private String brand;
		
	private int productionYear;
		
	private CurrencyType currencyType;
	private BigDecimal damagePrice;
		
	private CarStatusType carStatusType;
}
