package com.manager.person.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.person.entity.Pessoa;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class PessoasDTO {

    private final String id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private final String nome;

    @NotEmpty
    @Size(min = 2, max = 100)
    private final String ultimoNome;

    @NotEmpty
    @CPF
    private final String cpf;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private final LocalDate aniversario;

    @Valid
    @NotEmpty
    private final List<TelefoneDTO> telefone;

    public static PessoasDTO buildPessoaEntity(Pessoa pessoa) {
        return PessoasDTO
                .builder()
                .id(pessoa.getId().toString())
                .nome(pessoa.getNome())
                .ultimoNome(pessoa.getUltimoNome())
                .cpf(pessoa.getCpf())
                .aniversario(pessoa.getAniversario())
                .telefone(TelefoneDTO.buildTelefoneListDTO(pessoa.getTelefone()))
                .build();
    }

    public static Pessoa buildPessoaEntity(PessoasDTO pessoasDTO) {
        return Pessoa
                .builder()
                .nome(pessoasDTO.getNome())
                .ultimoNome(pessoasDTO.getUltimoNome())
                .cpf(pessoasDTO.getCpf())
                .aniversario(pessoasDTO.getAniversario())
                .telefone(TelefoneDTO.buildTelefoneListEntity(pessoasDTO.getTelefone()))
                .build();
    }
}
