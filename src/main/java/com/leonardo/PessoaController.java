package com.leonardo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@GetMapping
	public List<Pessoa> listar() {
		return service.listar();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Pessoa cadastrar(@RequestBody Pessoa pessoa) {
		return service.cadastrar(pessoa);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscar(@PathVariable Long id) {
		Optional<Pessoa> pessoa = service.findById(id);

		if (pessoa.isPresent())
			return ResponseEntity.ok(pessoa.get());

		return ResponseEntity.notFound().build();
	}
}
