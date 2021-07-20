package com.dominio.api.controller.users;

import com.dominio.api.dto.user.PessoaDTO;
import com.dominio.api.exception.NegocioException;
import com.dominio.api.model.user.Pessoa;
import com.dominio.api.service.user.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable final String uuid, @RequestBody final Pessoa pessoa) {

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
