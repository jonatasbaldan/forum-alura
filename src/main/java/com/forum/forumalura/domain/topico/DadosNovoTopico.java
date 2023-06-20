package com.forum.forumalura.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosNovoTopico(
        @NotBlank(message = "{titulo.obrigatorio}")
        String titulo,
        @NotBlank(message = "{mensagem.obrigatorio}")
        String mensagem,
        @NotBlank(message = "{autor.obrigatorio}")
        String autor,
        @NotBlank(message = "{curso.obrigatorio}")
        String curso
) {
}
