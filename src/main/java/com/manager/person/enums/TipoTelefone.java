package com.manager.person.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoTelefone {

    HOME("Casa"),
    MOBILE("Movel"),
    COMMERCIAL("Trabalho");

    private final String descricao;
}
