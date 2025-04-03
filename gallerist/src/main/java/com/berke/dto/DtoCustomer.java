package com.berke.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class DtoCustomer extends DtoBase{
	private String firstName;
	private String lastName;
	private String tckn;
	
	private Date birthOfDate;
	
	private DtoAddress address;
	
	private DtoAccount account;
}
