package com.berke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.berke.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	@Query(value = "from Account where id=:id",nativeQuery = false)
	Account findAccountById(Long id);
}
