package com.berke.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomerIU {
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private String tckn;
	@NotNull
	private Date birthOfDate;
	@NotNull
	private Long addressId;
	@NotNull
	private Long accountId;
}
