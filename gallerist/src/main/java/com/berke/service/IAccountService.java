package com.berke.service;

import com.berke.dto.DtoAccount;
import com.berke.dto.DtoAccountIU;

public interface IAccountService {
	public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
