package com.berke.service;

import com.berke.dto.DtoCustomer;
import com.berke.dto.DtoCustomerIU;

public interface ICustomerService {
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
