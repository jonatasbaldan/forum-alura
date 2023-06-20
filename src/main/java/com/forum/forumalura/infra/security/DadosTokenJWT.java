package com.forum.forumalura.infra.security;

import jakarta.validation.constraints.NotBlank;

public record DadosTokenJWT (
        @NotBlank
        String token
){
}
