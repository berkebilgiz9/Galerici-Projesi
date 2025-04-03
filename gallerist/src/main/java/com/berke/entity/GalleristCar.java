package com.berke.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gallerist_car", uniqueConstraints = {@UniqueConstraint(columnNames = {"gallerist_id" , "car_id"},name = "uq_gallerist_car")})
@NoArgsConstructor
@AllArgsConstructor
public class GalleristCar extends BaseEntity{
	@ManyToOne
	private Gallerist gallerist;
	@ManyToOne
	private Car car;
}
