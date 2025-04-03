package com.berke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.berke.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{
	@Query(value = "from Address where id=:id",nativeQuery = false)
	Address findAddressById(Long id);
}
