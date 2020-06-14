package com.packt.cardatabase.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packt.cardatabase.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByUsername(String username);
}
