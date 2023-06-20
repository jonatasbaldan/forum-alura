package com.forum.forumalura.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(
        @NotNull
        Long id,

        String titulo,

        String mensagem,

        EstadoTopico estado) {
}