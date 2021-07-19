package com.dominio.api.repository.file;

import com.dominio.api.model.file.Files;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<Files, String> {

}
