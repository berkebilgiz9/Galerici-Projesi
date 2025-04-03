package com.berke.service;

import com.berke.dto.DtoAddress;
import com.berke.dto.DtoAddressIU;

public interface IAddressService {
	public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
	public DtoAddress findAddressById(Long id);
}
