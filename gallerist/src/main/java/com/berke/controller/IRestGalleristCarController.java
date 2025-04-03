package com.berke.controller;

import com.berke.dto.DtoGalleristCar;
import com.berke.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {
	public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
