package com.dominio.api.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dominio.api.dto.PessoaDTO;
import com.dominio.api.model.Pessoa;
import com.dominio.api.repository.PessoaRepository;

@Service
public class PessoaService implements CrudAPI<Pessoa, PessoaDTO> {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public ResponseEntity<List<Pessoa>> listar() {
		List<Pessoa> pessoas = repository.findAll();
		return ResponseEntity.ok().body(pessoas);
	}

	@Override
	public ResponseEntity<Pessoa> cadastrar(Pessoa pessoa) {

		pessoa.setCadastro(OffsetDateTime.now());

		repository.save(pessoa);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	public ResponseEntity<PessoaDTO> buscar(Long id) {

		Optional<Pessoa> pessoa = repository.findById(id);

		PessoaDTO dto = toDTO(pessoa.get());

		if (pessoa.isPresent())
			return ResponseEntity.ok().body(dto);

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

	private PessoaDTO toDTO(Pessoa pessoa) {
		return modelmapper.map(pessoa, PessoaDTO.class);
	}
}
