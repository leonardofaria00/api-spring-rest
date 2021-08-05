package com.dominio.api.controller.users;

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

import com.dominio.api.dto.user.PessoaDTO;
import com.dominio.api.model.user.Pessoa;
import com.dominio.api.service.user.PessoaService;

@RestController
@RequestMapping(path = "/users/v1")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping
	public ResponseEntity<List<PessoaDTO>> listarPessoas() {
		return service.listarTodos();
	}

	@PostMapping
	public ResponseEntity<PessoaDTO> cadastrarPessoa(@Validated @RequestBody final Pessoa pessoa) {
		return service.cadastrar(pessoa);
	}

	@GetMapping("/{uuid}")
	public ResponseEntity<PessoaDTO> buscarPessoa(@PathVariable final String uuid) {
		return service.buscarPorId(uuid);
	}

	@PutMapping("/{uuid}")
	public ResponseEntity<PessoaDTO> atualizarPessoa(@Validated @PathVariable final String uuid,
			@RequestBody final Pessoa pessoa) {
		return service.atualizarPorId(uuid, pessoa);
	}

	@DeleteMapping("/{uuid}")
	public ResponseEntity<Void> removerPessoa(@PathVariable final String uuid) {
		return service.removerPorId(uuid);
	}

	@GetMapping("/mock/{uuid}")
	public Pessoa buscarPorIdMock(@PathVariable final String uuid) {
		return service.buscarPorIdMock(uuid);
	}
}
