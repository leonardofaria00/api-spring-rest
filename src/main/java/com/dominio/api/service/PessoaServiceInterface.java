package com.dominio.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface PessoaServiceInterface<E, DTO> {

	ResponseEntity<List<DTO>> listarTodos();

	ResponseEntity<DTO> cadastrar(E entity);

	ResponseEntity<DTO> buscarPorId(String id);

	ResponseEntity<DTO> atualizarPorId(String id, E entity);

	ResponseEntity<Void> removerPorId(String id);

	DTO toDTO(E entity);

	List<DTO> toListDTO(List<E> entity);

}
