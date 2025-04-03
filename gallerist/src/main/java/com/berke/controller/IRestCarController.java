package com.berke.controller;

import com.berke.dto.DtoCar;
import com.berke.dto.DtoCarIU;

public interface IRestCarController {
	public RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
