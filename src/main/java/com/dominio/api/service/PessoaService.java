package com.dominio.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dominio.api.model.Pessoa;
import com.dominio.api.repository.PessoaRepository;

@Service
public class PessoaService implements CRUD_API<Pessoa>{

	@Autowired
	private PessoaRepository repository;

	@Override
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> pessoas = repository.findAll();
		return ResponseEntity.ok().body(pessoas);
	}

	@Override
	public ResponseEntity<Pessoa> cadastrar(Pessoa pessoa) {
		repository.save(pessoa);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	public ResponseEntity<Pessoa> buscar(Long id) {
		Optional<Pessoa> pessoa = repository.findById(id);

		if (pessoa.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(pessoa.get());

		return ResponseEntity.notFound().build();
	}

	@Override
	public ResponseEntity<Void> remover(Long id) {
		repository.deleteById(id);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@Override
	public ResponseEntity<Pessoa> atualizar(Long id, Pessoa pessoa) {
		if (repository.existsById(id)) {
			pessoa.setId(id);
			repository.save(pessoa);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
