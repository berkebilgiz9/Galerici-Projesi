package com.berke.service;

import com.berke.dto.DtoCar;
import com.berke.dto.DtoCarIU;

public interface ICarService {
	public DtoCar saveCar(DtoCarIU dtoCarIU);
}
