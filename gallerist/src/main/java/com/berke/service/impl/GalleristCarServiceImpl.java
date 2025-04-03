package com.berke.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berke.dto.DtoAddress;
import com.berke.dto.DtoCar;
import com.berke.dto.DtoGallerist;
import com.berke.dto.DtoGalleristCar;
import com.berke.dto.DtoGalleristCarIU;
import com.berke.entity.Car;
import com.berke.entity.Gallerist;
import com.berke.entity.GalleristCar;
import com.berke.exception.BaseException;
import com.berke.exception.ErrorMessage;
import com.berke.exception.MessageType;
import com.berke.repository.CarRepository;
import com.berke.repository.GalleristCarRepository;
import com.berke.repository.GalleristRepository;
import com.berke.service.IGalleristCarService;
@Service
public class GalleristCarServiceImpl implements IGalleristCarService{
	@Autowired
	private GalleristCarRepository galleristCarRepository;
	@Autowired
	private GalleristRepository galleristRepository;
	@Autowired
	private CarRepository carRepository;
	
	private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		GalleristCar galleristCar = new GalleristCar();
		galleristCar.setCreateTime(new Date());
		Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
		if(optGallerist.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
		Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
		if(optCar.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
		galleristCar.setCar(optCar.get());
		galleristCar.setGallerist(optGallerist.get());
		return galleristCar;
	}

	@Override
	public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCar dtoCar = new DtoCar();
		DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
		GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
		DtoAddress dtoAddress= new DtoAddress();
		BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
		dtoGallerist.setAddress(dtoAddress);
		BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);	
		BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);
		BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
		dtoGalleristCar.setCar(dtoCar);
		dtoGalleristCar.setGallerist(dtoGallerist);
		return dtoGalleristCar;
	}
}
