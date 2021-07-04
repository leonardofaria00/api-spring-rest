package com.dominio.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dominio.api.model.Pessoa;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {

}
