package com.dominio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dominio.api.model.Files;

@Repository
public interface FileRepository extends JpaRepository<Files, Long> {

}
