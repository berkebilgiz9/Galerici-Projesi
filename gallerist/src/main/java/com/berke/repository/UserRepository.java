package com.berke.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.berke.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByUsername(String username);
	//@Query(value = "from User u where u.username=:username",nativeQuery = false)
	//User findByUsername(String username);
}
