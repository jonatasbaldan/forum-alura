package com.forum.forumalura.domain.topico;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "Topico")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo", "mensagem"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private EstadoTopico estado;
    private String autor;
    private String curso;
    private boolean ativo;

    public Topico(DadosNovoTopico dadosTopico) {
        this.titulo = dadosTopico.titulo();
        this.mensagem = dadosTopico.mensagem();
        this.autor = dadosTopico.autor();
        this.curso = dadosTopico.curso();
        this.dataCriacao = LocalDateTime.now();
        this.estado = EstadoTopico.ABERTO;
        this.ativo = true;
    }

    public void excluir() {
        ativo = false;
    }

    public void atualizar(DadosAtualizacaoTopico dadosAtualizacaoTopico) {
        if (Objects.nonNull(dadosAtualizacaoTopico.titulo()))
            this.titulo = dadosAtualizacaoTopico.titulo();

        if (Objects.nonNull(dadosAtualizacaoTopico.mensagem()))
            this.mensagem = dadosAtualizacaoTopico.mensagem();

        if (Objects.nonNull(dadosAtualizacaoTopico.estado()))
            this.estado = dadosAtualizacaoTopico.estado();
    }
}
