package com.forum.forumalura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosListagemTopico(@NotNull
                                  Long id,
                                  @NotBlank
                                  String titulo,
                                  @NotBlank
                                  String mensagem,
                                  @NotBlank
                                  String autor,
                                  @NotBlank
                                  String curso,
                                  @NotNull
                                  LocalDateTime dataCriacao,
                                  @NotNull
                                  EstadoTopico estado) {

    public DadosListagemTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor(),
                topico.getCurso(),
                topico.getDataCriacao(),
                topico.getEstado()
        );
    }
}
