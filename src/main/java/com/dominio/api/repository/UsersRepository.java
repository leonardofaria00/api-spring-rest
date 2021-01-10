package com.dominio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dominio.api.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
}
