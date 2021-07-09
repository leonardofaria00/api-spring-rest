package com.dominio.api.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dominio.api.model.file.Files;

@Repository
public interface FileRepository extends JpaRepository<Files, Long> {

}
