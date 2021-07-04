package com.dominio.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominio.api.dto.PessoaDTO;
import com.dominio.api.model.Pessoa;
import com.dominio.api.service.PessoaService;

@RestController
@RequestMapping(path = "/users")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<List<PessoaDTO>> listarPessoas() {
		return service.listar();
	}

	@PostMapping
	public ResponseEntity<PessoaDTO> cadastrarPessoa(@Validated @RequestBody final Pessoa pessoa) {
		return service.cadastrar(pessoa);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaDTO> buscarPessoa(@PathVariable final Long id) {
		return service.buscar(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable final Long id, @RequestBody final Pessoa pessoa) {
		return service.atualizar(id, pessoa);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerPessoa(@PathVariable final Long id) {
		return service.remover(id);
	}
}
