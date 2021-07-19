package com.dominio.api.repository.nasa;

import com.dominio.api.model.nasa.Nasa;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasaReposiroty extends MongoRepository<Nasa, String> {

}
