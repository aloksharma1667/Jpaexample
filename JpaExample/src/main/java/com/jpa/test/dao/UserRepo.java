package com.jpa.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.jpa.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	
}
