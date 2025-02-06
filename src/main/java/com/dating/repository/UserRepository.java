package com.dating.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dating.entities.ProcessingUser;
import com.dating.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
	
}

