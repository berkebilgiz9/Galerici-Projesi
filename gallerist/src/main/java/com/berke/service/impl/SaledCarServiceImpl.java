package com.berke.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berke.dto.CurrencyRatesResponse;
import com.berke.dto.DtoAccount;
import com.berke.dto.DtoAddress;
import com.berke.dto.DtoCar;
import com.berke.dto.DtoCustomer;
import com.berke.dto.DtoGallerist;
import com.berke.dto.DtoSaledCar;
import com.berke.dto.DtoSaledCarIU;
import com.berke.entity.Car;
import com.berke.entity.Customer;
import com.berke.entity.SaledCar;
import com.berke.enums.CarStatusType;
import com.berke.exception.BaseException;
import com.berke.exception.ErrorMessage;
import com.berke.exception.MessageType;
import com.berke.repository.CarRepository;
import com.berke.repository.CustomerRepository;
import com.berke.repository.GalleristRepository;
import com.berke.repository.SaledCarRepository;
import com.berke.service.ICurrencyRatesService;
import com.berke.service.ISaledCarService;
import com.berke.utils.DateUtils;
@Service
public class SaledCarServiceImpl implements ISaledCarService{
	@Autowired
	private GalleristRepository galleristRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ICurrencyRatesService currencyRatesService;
	@Autowired
	private SaledCarRepository saledCarRepository;
	
	public BigDecimal convertCustomerAmountToUSD(Customer customer) {
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		BigDecimal amount= customer.getAccount().getAmount();
		return amount.divide(usd, 2, RoundingMode.HALF_UP);
	}
	
	public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {
		Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
		if(optCustomer.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
		Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
		if(optCar.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
		BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());
		if(customerUSDAmount.compareTo(optCar.get().getPrice())>=0) {
			return true;
		}
		return false;
	}
	
	private boolean isCarSalable(Long carId) {
		 Optional<Car> optCar = carRepository.findById(carId);
		 if(optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
			 return false;
		 }
		 return true;
	}
	
	private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
		SaledCar saledCar=new SaledCar();
		saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));
		saledCar.setCreateTime(new Date());
		saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
		saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
		return saledCar;
	}
	
	public BigDecimal remainingCustomerAcount(Customer customer,Car car) {
		BigDecimal customerAmountUSD = convertCustomerAmountToUSD(customer);
		BigDecimal remainingCustomerUSDAmount = customerAmountUSD.subtract(car.getPrice());
		CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
		BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());
		return remainingCustomerUSDAmount.multiply(usd);
	}
	
	@Override 
	public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
		if(isCarSalable(dtoSaledCarIU.getCarId())) {	
			throw new BaseException(new ErrorMessage(MessageType.CAR_STATUS_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
		}
		if(!checkAmount(dtoSaledCarIU)) {
			throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, customerRepository.findById(dtoSaledCarIU.getCustomerId()).get().getAccount().getAmount().toString()));
		}
		SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
		Car car= savedSaledCar.getCar();
		car.setCarStatusType(CarStatusType.SALED);
		Customer customer =savedSaledCar.getCustomer();
		customer.getAccount().setAmount(remainingCustomerAcount(customer, car));
		customerRepository.save(customer);
		
		return toDTO(savedSaledCar);
	}
	
	public DtoSaledCar toDTO(SaledCar savedSaledCar) {
		DtoSaledCar dtoSaledCar = new DtoSaledCar();
		DtoGallerist dtoGallerist = new DtoGallerist();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoCar dtoCar = new DtoCar();
		BeanUtils.copyProperties(savedSaledCar.getGallerist(), dtoGallerist);
		DtoAddress dtoAddress = new DtoAddress();
		DtoAccount dtoAccount = new DtoAccount();
		BeanUtils.copyProperties(savedSaledCar.getGallerist().getAddress(), dtoAddress);
		dtoGallerist.setAddress(dtoAddress);
		BeanUtils.copyProperties(savedSaledCar.getCar(), dtoCar);
		BeanUtils.copyProperties(savedSaledCar.getCustomer(), dtoCustomer);
		BeanUtils.copyProperties(savedSaledCar.getCustomer().getAccount(), dtoAccount);
		dtoCustomer.setAddress(dtoAddress);
		dtoCustomer.setAccount(dtoAccount);
		dtoSaledCar.setCar(dtoCar);
		dtoSaledCar.setCustomer(dtoCustomer);
		dtoSaledCar.setGallerist(dtoGallerist);
		return dtoSaledCar;
	}
}
