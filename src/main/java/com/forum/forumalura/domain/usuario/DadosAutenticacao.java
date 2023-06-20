package com.forum.forumalura.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank
        String usuario,
        @NotBlank
        String senha
) {
}
