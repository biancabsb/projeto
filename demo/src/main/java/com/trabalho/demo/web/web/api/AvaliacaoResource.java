
package com.trabalho.demo.web.web.api;


import com.trabalho.demo.domain.Avaliacao;
import com.trabalho.demo.service.AvaliacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoResource {
    private final Logger log = LoggerFactory.getLogger(AvaliacaoResource.class);

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoResource(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    /**
     * {@code GET  /avaliacoes/:id} : get the "id" avaliacao.
     *
     * @param id o id do avaliacao que ser?? buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o avaliacao, ou com status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Avaliacao> getAvaliacao(@PathVariable Long id) {
        log.debug("REST request to get Avaliacao : {}", id);
        Optional<Avaliacao> avaliacao = avaliacaoService.findOne(id);
        if(avaliacao.isPresent()) {
            return ResponseEntity.ok().body(avaliacao.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Avaliacao>> getAvaliacoes(){
        List<Avaliacao> lista = avaliacaoService.findAllList();
        if(lista.size() > 0) {
            return ResponseEntity.ok().body(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code PUT  /pessoas} : Atualiza um pessoa existenteUpdate.
     *
     * @param pessoa o pessoa a ser atulizado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no corpo o pessoa atualizado,
     * ou com status {@code 400 (Bad Request)} se o pessoa n??o ?? v??lido,
     * ou com status {@code 500 (Internal Server Error)} se o pessoa n??o pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/")
    public ResponseEntity<Avaliacao> updateAvaliacao(@RequestBody Avaliacao avaliacao) throws URISyntaxException {
        log.debug("REST request to update Avaliacao : {}", avaliacao);
        if (avaliacao.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Avaliacao id null");
        }
        Avaliacao result =avaliacaoService.save(avaliacao);
        return ResponseEntity.ok()
                .body(result);
    }

    /**
     * {@code POST  /} : Create a new pessoa.
     *
     * @param pessoa the pessoa to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new pessoa, or with status {@code 400 (Bad Request)} if the pessoa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/")
    public ResponseEntity<Avaliacao> createAvaliacao(@RequestBody Avaliacao avaliacao) throws URISyntaxException {
        log.debug("REST request to save Avaliacao : {}", avaliacao);
        if (avaliacao.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo avaliacao n??o pode terum ID");
        }
        Avaliacao result = avaliacaoService.save(avaliacao);
        return ResponseEntity.created(new URI("/api/avaliacoes/" + result.getId()))
                .body(result);
    }

    @PostMapping(value = "/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Avaliacao> upload(@RequestPart("data") MultipartFile csv) throws IOException {
        List<Avaliacao> savedNotes = new ArrayList<>();
        List<Avaliacao> notes = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(csv).getInputStream(), StandardCharsets.UTF_8)).lines()
                .map(Avaliacao::parseNote).collect(Collectors.toList());
        avaliacaoService.saveAll(notes).forEach(savedNotes::add);
        return savedNotes;
    }

    /**
     * {@code DELETE  /:id} : delete pelo "id" avaliacao.
     *
     * @param id o id do pessoas que ser?? delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        log.debug("REST request to delete Avaliacao : {}", id);

        avaliacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
