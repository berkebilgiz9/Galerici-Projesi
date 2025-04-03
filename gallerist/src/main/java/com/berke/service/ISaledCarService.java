package com.berke.service;

import com.berke.dto.DtoSaledCar;
import com.berke.dto.DtoSaledCarIU;

public interface ISaledCarService {
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
