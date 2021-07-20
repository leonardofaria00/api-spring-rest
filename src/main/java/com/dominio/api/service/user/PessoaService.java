package com.dominio.api.service.user;

import com.dominio.api.dto.user.PessoaDTO;
import com.dominio.api.exception.NegocioException;
import com.dominio.api.model.user.Pessoa;
import com.dominio.api.repository.user.PessoaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<PessoaDTO> cadastrar(final Pessoa pessoa) {

        this.validateUsertoSave(pessoa);

        try {
            repository.save(this.cleanDataToSave(pessoa));
        } catch (Exception e) {
            throw new NegocioException("Error to save User.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<PessoaDTO> buscarPorId(String uuid) {

        Optional<Pessoa> pessoa = repository.findById(uuid);

        if (pessoa.isPresent()) {
            PessoaDTO dto = toDTO(pessoa.get());
            return ResponseEntity.ok().body(dto);
        }
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PessoaDTO> atualizarPorId(String uuid, Pessoa pessoa) {

        this.validateUsertoSave(pessoa);

        if (repository.existsById(uuid)) {
            pessoa.setUuid(uuid);
            repository.save(this.cleanDataToSave(pessoa));
            return ResponseEntity.status(HttpStatus.OK).build();// Demostrando outras formas de implementar
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();// Demostrando outras formas de implementar
    }

    @Override
    public ResponseEntity<Void> removerPorId(String uuid) {
        try {
            repository.deleteById(uuid);
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
        return pessoas.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private void validateUsertoSave(Pessoa pessoa) {
        if (pessoa.getName().trim().isEmpty())
            throw new NegocioException("Name cannot be empty");

        if (pessoa.getAge() == null || pessoa.getAge() <= 0 || pessoa.getAge() > 120)
            throw new NegocioException("Invalid age, enter an age between 1 and 120 years.");
    }

    private Pessoa cleanDataToSave(Pessoa pessoa) {
        pessoa.setCreatedAt(LocalDateTime.now());
        pessoa.setName(pessoa.getName().trim());
        return pessoa;
    }

    public Pessoa buscarPorIdMock(String uuid) {
        return new Pessoa(uuid, "Leonardo", 21, LocalDateTime.now());
    }
}
