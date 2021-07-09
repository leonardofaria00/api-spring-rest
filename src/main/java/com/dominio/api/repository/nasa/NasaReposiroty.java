package com.dominio.api.repository.nasa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dominio.api.model.nasa.Nasa;

@Repository
public interface NasaReposiroty extends JpaRepository<Nasa, Long> {

}
