package com.berke.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.berke.controller.IRestCarController;
import com.berke.controller.RestBaseController;
import com.berke.controller.RootEntity;
import com.berke.dto.DtoCar;
import com.berke.dto.DtoCarIU;
import com.berke.service.ICarService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController{
	@Autowired
	private ICarService carService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.saveCar(dtoCarIU));
	}
	
	
}
