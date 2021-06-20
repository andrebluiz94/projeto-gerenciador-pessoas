package com.manager.person.controller;


import com.manager.person.controller.dto.MensagemRetornoDTO;
import com.manager.person.controller.dto.PessoasDTO;
import com.manager.person.service.PessoasService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/pessoas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private final PessoasService service;

    @GetMapping
    public List<PessoasDTO> getPessoas() {
        return service.buscarPessoas();
    }

    @GetMapping("/id/{id_pessoa}")
    public PessoasDTO getPessoa(@PathVariable("id_pessoa") String id) {
        return service.buscarPessoas(id);
    }

    @PostMapping
    public ResponseEntity<MensagemRetornoDTO> savePessoa(@RequestBody PessoasDTO pessoasDTO) {
        return service.salvarPessoa(pessoasDTO);
    }

    @PutMapping("/{id_pessoa}")
    public ResponseEntity<MensagemRetornoDTO> alterarPessoaCompleta(@PathVariable String id_pessoa, @RequestBody PessoasDTO pessoasDTO) {
        return service.alterarPessoa(id_pessoa, pessoasDTO);
    }

    @DeleteMapping("/{id_pessoa}")
    public ResponseEntity<MensagemRetornoDTO> alterarPessoaCompleta(@PathVariable("id_pessoa") String id) {
        return service.delete(id);
    }

}
