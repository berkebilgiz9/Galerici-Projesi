package com.berke.entity;

import java.math.BigDecimal;

import com.berke.enums.CarStatusType;
import com.berke.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseEntity{
	private String plaka;
	private String model;
	private BigDecimal price;
	private String brand;
	@Column(name = "production_year")
	private int productionYear;
	@Enumerated(EnumType.STRING)
	private CurrencyType currencyType;
	private BigDecimal damagePrice;
	@Enumerated(EnumType.STRING)
	private CarStatusType carStatusType;
}
