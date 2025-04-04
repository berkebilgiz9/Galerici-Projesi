package com.berke.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berke.controller.IRestGalleristCarController;
import com.berke.controller.RestBaseController;
import com.berke.controller.RootEntity;
import com.berke.dto.DtoGalleristCar;
import com.berke.dto.DtoGalleristCarIU;
import com.berke.service.IGalleristCarService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController{
	@Autowired
	private IGalleristCarService galleristCarService;

	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}
}
