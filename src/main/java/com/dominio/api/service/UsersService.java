package com.dominio.api.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dominio.api.dto.PessoaDTO;
import com.dominio.api.exception.NegocioException;
import com.dominio.api.model.Users;
import com.dominio.api.repository.UsersRepository;

@Service
public class UsersService implements CrudAPIService<Users, PessoaDTO> {

	@Autowired
	private UsersRepository repository;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public ResponseEntity<List<PessoaDTO>> listar() {

		List<Users> pessoas = repository.findAll();

		if (!pessoas.isEmpty()) {
			List<PessoaDTO> dto = toListDTO(pessoas);
			return ResponseEntity.ok().body(dto);
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PessoaDTO> cadastrar(Users pessoa) {

		pessoa.setCreatedAt(OffsetDateTime.now());

		repository.save(pessoa);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@Override
	public ResponseEntity<PessoaDTO> buscar(Long id) {

		Optional<Users> pessoa = repository.findById(id);

		if (pessoa.isPresent()) {
			PessoaDTO dto = toDTO(pessoa.get());
			return ResponseEntity.ok().body(dto);
		}
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<PessoaDTO> atualizar(Long id, Users pessoa) {
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
			throw new NegocioException("Pessoa não encontrada!");
		}

		return ResponseEntity.noContent().build();
	}

	@Override
	public PessoaDTO toDTO(Users pessoa) {
		return modelmapper.map(pessoa, PessoaDTO.class);
	}

	@Override
	public List<PessoaDTO> toListDTO(List<Users> pessoas) {
		return pessoas.stream().map(pessoa -> toDTO(pessoa)).collect(Collectors.toList());
	}
}
