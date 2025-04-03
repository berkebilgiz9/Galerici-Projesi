package com.berke.controller;

import com.berke.dto.DtoAccount;
import com.berke.dto.DtoAccountIU;

public interface IRestAccountController {
	public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
