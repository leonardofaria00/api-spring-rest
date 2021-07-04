package com.dominio.api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dominio.api.domain.PessoaDTO;
import com.dominio.api.exception.NegocioException;
import com.dominio.api.model.Pessoa;
import com.dominio.api.repository.PessoaRepository;

@Service
public class PessoaService implements PessoaServiceInterface<Pessoa, PessoaDTO> {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public ResponseEntity<List<PessoaDTO>> listarTodos() {

		List<Pessoa> pessoas = repository.findAll();

		if (!pessoas.isEmpty()) {
			List<PessoaDTO> dto = toListDTO(pessoas);
			return ResponseEntity.ok().body(dto);
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PessoaDTO> cadastrar(Pessoa pessoa) {

		pessoa.setCadastro(LocalDateTime.now());

		repository.save(pessoa);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	public ResponseEntity<PessoaDTO> buscarPorId(String id) {

		Optional<Pessoa> pessoa = repository.findById(id);

		if (pessoa.isPresent()) {
			PessoaDTO dto = toDTO(pessoa.get());
			return ResponseEntity.ok().body(dto);
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PessoaDTO> atualizarPorId(String id, Pessoa pessoa) {
		if (repository.existsById(id)) {
			pessoa.setId(id);
			repository.save(pessoa);
			return ResponseEntity.status(HttpStatus.OK).build();// Demostrando outras formas de implementar retornos com
																// o ENUM HttpStatus.
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	public ResponseEntity<Void> removerPorId(String id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new NegocioException("Pessoa não encontrada!");
		}

		return ResponseEntity.noContent().build();
	}

	@Override
	public PessoaDTO toDTO(Pessoa pessoa) {
		return modelmapper.map(pessoa, PessoaDTO.class);
	}

	@Override
	public List<PessoaDTO> toListDTO(List<Pessoa> pessoas) {
		return pessoas.stream().map(pessoa -> toDTO(pessoa)).collect(Collectors.toList());
	}

	public Pessoa buscarPorIdMock(String id) {
		return new Pessoa("xpto", "Leonardo", LocalDateTime.now());
	}
}
