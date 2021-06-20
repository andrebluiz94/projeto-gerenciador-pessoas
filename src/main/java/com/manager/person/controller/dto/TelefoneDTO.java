package com.manager.person.controller.dto;

import com.manager.person.entity.Telefone;
import com.manager.person.enums.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTO {

    private String id;

    @Enumerated(EnumType.STRING)
    private TipoTelefone tipo;
    @NotEmpty
    @Size(min = 13, max = 14)
    private String numero;

    public static List<TelefoneDTO> buildTelefoneListDTO(List<Telefone> telefoneList){
        return telefoneList
                .stream()
                .map(telefone -> TelefoneDTO
                        .builder()
                        .id(telefone.getId().toString())
                        .numero(telefone.getNumero())
                        .tipo(telefone.getTipo())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

    public static List<Telefone> buildTelefoneListEntity(List<TelefoneDTO> telefoneList){
        return telefoneList
                .stream()
                .map(telefone -> Telefone
                        .builder()
                        .numero(telefone.getNumero())
                        .tipo(telefone.getTipo())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

}
