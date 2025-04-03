package com.berke.controller;

import com.berke.dto.DtoAddress;
import com.berke.dto.DtoAddressIU;

public interface IRestAddressController {
	public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
	public RootEntity<DtoAddress> findAddressById(Long id);
}
