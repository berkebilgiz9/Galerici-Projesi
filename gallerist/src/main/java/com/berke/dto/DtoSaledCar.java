package com.berke.dto;

import lombok.Data;

@Data
public class DtoSaledCar extends DtoBase{
	private DtoGallerist gallerist;
	private DtoCar car;
	private DtoCustomer customer;
}
