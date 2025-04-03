package com.berke.controller;

import com.berke.dto.DtoSaledCar;
import com.berke.dto.DtoSaledCarIU;

public interface IRestSaledCarController {
	public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
