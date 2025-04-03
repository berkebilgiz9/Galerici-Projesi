package com.berke.controller;

import com.berke.dto.DtoCustomer;
import com.berke.dto.DtoCustomerIU;

public interface IRestCustomerController {
	public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
