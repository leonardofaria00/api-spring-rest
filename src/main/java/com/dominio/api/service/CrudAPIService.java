package com.dominio.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CrudAPIService<E, DTO> {

	ResponseEntity<List<DTO>> listar();

	ResponseEntity<DTO> cadastrar(E entity);

	ResponseEntity<DTO> buscar(Long id);

	ResponseEntity<Void> remover(Long id);

	ResponseEntity<DTO> atualizar(Long id, E entity);

	DTO toDTO(E entity);

	List<DTO> toListDTO(List<E> entity);

}
