package com.dominio.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dominio.api.model.Files;

@Repository
public interface FileRepository extends MongoRepository<Files, String> {

}
