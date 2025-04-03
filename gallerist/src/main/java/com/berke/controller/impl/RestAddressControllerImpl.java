package com.berke.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berke.controller.IRestAddressController;
import com.berke.controller.RestBaseController;
import com.berke.controller.RootEntity;
import com.berke.dto.DtoAddress;
import com.berke.dto.DtoAddressIU;
import com.berke.service.IAddressService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/rest/api/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController{
	@Autowired
	private IAddressService addressService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
		return ok(addressService.saveAddress(dtoAddressIU));
	}
	
	@GetMapping(path = "/{id}")
	@Override
	public RootEntity<DtoAddress> findAddressById(@PathVariable(name = "id",required = true) Long id) {
		return ok(addressService.findAddressById(id));
	}

}
