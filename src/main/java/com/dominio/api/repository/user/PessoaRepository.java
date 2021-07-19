package com.dominio.api.repository.user;

import com.dominio.api.model.user.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends MongoRepository <Pessoa, String> {

}
