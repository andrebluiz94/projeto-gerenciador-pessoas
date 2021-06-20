package com.manager.person.service;

import com.manager.person.controller.dto.MensagemRetornoDTO;
import com.manager.person.controller.dto.PessoasDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PessoasService {
    List<PessoasDTO> buscarPessoas();

    PessoasDTO buscarPessoas(final String id_pessoa);

    ResponseEntity<MensagemRetornoDTO> salvarPessoa(final PessoasDTO pessoasDTO);

    ResponseEntity<MensagemRetornoDTO> alterarPessoa(final String id_pessoa, final PessoasDTO pessoasDTO);

    ResponseEntity<MensagemRetornoDTO> delete(String id);
}
