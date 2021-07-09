package com.dominio.api.service.user;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface PessoaServiceInterface<E, DTO> {

	ResponseEntity<List<DTO>> listarTodos();

	ResponseEntity<DTO> cadastrar(E entity);

	ResponseEntity<DTO> buscarPorId(Long id);

	ResponseEntity<DTO> atualizarPorId(Long id, E entity);

	ResponseEntity<Void> removerPorId(Long id);

	DTO toDTO(E entity);

	List<DTO> toListDTO(List<E> entity);

}
