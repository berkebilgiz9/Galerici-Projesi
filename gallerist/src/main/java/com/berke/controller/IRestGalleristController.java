package com.berke.controller;

import com.berke.dto.DtoGallerist;
import com.berke.dto.DtoGalleristIU;

public interface IRestGalleristController {
	public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
