package com.manager.person.service.impl;

import com.manager.person.controller.dto.MensagemRetornoDTO;
import com.manager.person.controller.dto.PessoasDTO;
import com.manager.person.entity.Pessoa;
import com.manager.person.entity.repository.PessoasRepository;
import com.manager.person.service.PessoasService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PessoasServiceImpl implements PessoasService {

    private final PessoasRepository repository;

    @Override
    public List<PessoasDTO> buscarPessoas() {
        return repository.findAll()
                .stream()
                .map(PessoasDTO::buildPessoaEntity)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public PessoasDTO buscarPessoas(final String id_pessoa) {

        try {

            return repository.findById(UUID.fromString(id_pessoa))
                    .map(PessoasDTO::buildPessoaEntity)
                    .orElseThrow();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pessoa nao encontrada.", e);
        }
    }

    @Override
    public ResponseEntity<MensagemRetornoDTO> salvarPessoa(final PessoasDTO pessoasDTO) {
        var pessoa = repository.saveAndFlush(PessoasDTO.buildPessoaEntity(pessoasDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(MensagemRetornoDTO
                .builder()
                .mensagem("Usuario salvo com sucesso. ID: " + pessoa.getId().toString())
                .build());
    }

    @Override
    public ResponseEntity<MensagemRetornoDTO> alterarPessoa(final String id_pessoa, final PessoasDTO pessoasDTO) {
        Pessoa id = repository.findById(UUID.fromString(id_pessoa))
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Pessoa nao encontrada."));
        try {
            Pessoa pessoa = PessoasDTO.buildPessoaEntity(pessoasDTO);
            pessoa.setId(id.getId());
            repository.save(pessoa);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(MensagemRetornoDTO.builder()
                            .mensagem(String.format("Usuario com ID[%s] atualizado com sucesso", id_pessoa))
                            .build());
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Falha ao atualizar as entidades.");
        }
    }

    @Override
    public ResponseEntity<MensagemRetornoDTO> delete(String id) {
        try {
            repository.deleteById(UUID.fromString(id));
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body(MensagemRetornoDTO
                            .builder()
                            .mensagem(String.format("Usuario com ID[%s] deletado com sucesso", id))
                            .build());
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Pessoa nao encontrada.");
        }
    }
}
