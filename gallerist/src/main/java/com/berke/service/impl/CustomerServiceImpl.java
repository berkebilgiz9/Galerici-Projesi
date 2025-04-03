package com.berke.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berke.dto.DtoAccount;
import com.berke.dto.DtoAddress;
import com.berke.dto.DtoCustomer;
import com.berke.dto.DtoCustomerIU;
import com.berke.entity.Account;
import com.berke.entity.Address;
import com.berke.entity.Customer;
import com.berke.exception.BaseException;
import com.berke.exception.ErrorMessage;
import com.berke.exception.MessageType;
import com.berke.repository.AccountRepository;
import com.berke.repository.AddressRepository;
import com.berke.repository.CustomerRepository;
import com.berke.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private AccountRepository accountRepository;
	
	private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
		Customer customer = new Customer();
		customer.setCreateTime(new Date());
		Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
		if(optAddress.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAddressId().toString()));
		Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
		if(optAccount.isEmpty())
			throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoCustomerIU.getAccountId().toString()));
		BeanUtils.copyProperties(dtoCustomerIU, customer);
		customer.setAccount(optAccount.get());
		customer.setAddress(optAddress.get());
		return customer;
	}
	
	@Override
	public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {  //veritabanına kaydetmek için repositorydeki save methodu kullanıldı
		Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoAccount dtoAccount = new DtoAccount();
		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
		BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);
		dtoCustomer.setAccount(dtoAccount);
		dtoCustomer.setAddress(dtoAddress);
		BeanUtils.copyProperties(savedCustomer, dtoCustomer);
		return dtoCustomer;
	}
}
