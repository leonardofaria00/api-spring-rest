package com.dominio.api.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dominio.api.dto.PessoaDTO;
import com.dominio.api.model.Pessoa;
import com.dominio.api.repository.PessoaRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PessoaService implements CrudAPIService<Pessoa, PessoaDTO> {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public ResponseEntity<List<PessoaDTO>> listar() {

		List<Pessoa> pessoas = repository.findAll();

		if (!pessoas.isEmpty()) {
			List<PessoaDTO> dto = toListDTO(pessoas);
			return ResponseEntity.ok().body(dto);
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PessoaDTO> cadastrar(Pessoa pessoa) {

		pessoa.setCadastro(OffsetDateTime.now());

		repository.save(pessoa);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	public ResponseEntity<PessoaDTO> buscar(Long id) {

		Optional<Pessoa> pessoa = repository.findById(id);

		if (pessoa.isPresent()) {
			PessoaDTO dto = toDTO(pessoa.get());
			return ResponseEntity.ok().body(dto);
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PessoaDTO> atualizar(Long id, Pessoa pessoa) {
		if (repository.existsById(id)) {
			pessoa.setId(id);
			repository.save(pessoa);
			return ResponseEntity.status(HttpStatus.OK).build();// Demostrando outras formas de implementar
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();// Demostrando outras formas de implementar
	}

	@Override
	public ResponseEntity<Void> remover(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
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
}
