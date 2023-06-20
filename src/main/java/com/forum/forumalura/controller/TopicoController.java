package com.forum.forumalura.controller;

import com.forum.forumalura.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("topico")
public class TopicoController {

    @Autowired
    TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosTopico> criarNovoTopico(@RequestBody @Valid DadosNovoTopico dados, UriComponentsBuilder uriBuilder) {
        Topico topico = new Topico(dados);
        repository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosTopico(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosTopico> detalhar(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(@PageableDefault(size = 10, sort = {"dataCriacao"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<DadosListagemTopico> page = repository
                .findAllByAtivoTrue(pageable)
                .map(DadosListagemTopico::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosListagemTopico> atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
        Topico topico = repository.getReferenceById(dados.id());
        topico.atualizar(dados);

        return ResponseEntity.ok(new DadosListagemTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        Topico topico = repository.getReferenceById(id);
        topico.excluir();

        return ResponseEntity.noContent().build();
    }
}
