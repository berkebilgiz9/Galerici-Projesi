package com.berke.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berke.dto.DtoAddress;
import com.berke.dto.DtoGallerist;
import com.berke.dto.DtoGalleristIU;
import com.berke.entity.Address;
import com.berke.entity.Gallerist;
import com.berke.exception.BaseException;
import com.berke.exception.ErrorMessage;
import com.berke.exception.MessageType;
import com.berke.repository.AddressRepository;
import com.berke.repository.GalleristRepository;
import com.berke.service.IGalleristService;
@Service
public class GalleristServiceImpl implements IGalleristService{
	@Autowired
	private GalleristRepository galleristRepository;
	@Autowired
	private AddressRepository addressRepository;
	private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {
		Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
		if(optAddress.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
		Gallerist gallerist = new Gallerist();
		gallerist.setCreateTime(new Date());
		gallerist.setAddress(optAddress.get());
		BeanUtils.copyProperties(dtoGalleristIU, gallerist);
		return gallerist;
	}
	
	@Override
	public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoAddress dtoAddress = new DtoAddress();
		Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
		BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
		dtoGallerist.setAddress(dtoAddress);
		BeanUtils.copyProperties(savedGallerist, dtoGallerist);
		return dtoGallerist;
	}

}
