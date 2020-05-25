package com.leonardo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	public List<Pessoa> listar() {
		return repository.findAll();
	}

	public Pessoa cadastrar(Pessoa pessoa) {
		return repository.save(pessoa);
	}

	public Optional<Pessoa> findById(Long id) {
		return repository.findById(id);
	}
}
