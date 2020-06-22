package com.dominio.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CrudAPI<E, DTO> {

	ResponseEntity<List<E>> listar();

	ResponseEntity<E> cadastrar(E o);

	ResponseEntity<DTO> buscar(Long id);

	ResponseEntity<Void> remover(Long id);

	ResponseEntity<E> atualizar(Long id, E pessoa);

}
