package com.berke.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoGalleristIU {
	@NotNull
	private String firstName;
	@NotNull
	private String lastName;
	@NotNull
	private Long addressId;
}
