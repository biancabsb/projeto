
package com.trabalho.demo.web.web.api;


import com.trabalho.demo.domain.Professor;
import com.trabalho.demo.service.ProfessorService;
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

import com.trabalho.demo.domain.Professor;
import com.trabalho.demo.service.ProfessorService;
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
@RequestMapping("/professores")
public class ProfessorResource {
    private final Logger log = LoggerFactory.getLogger(ProfessorResource.class);

    private final ProfessorService professorService;

    public ProfessorResource(ProfessorService professorService) {
        this.professorService = professorService;
    }

    /**
     * {@code GET  /professores/:id} : get the "id" professor.
     *
     * @param id o id do professor que será buscado.
     * @return o {@link ResponseEntity} com status {@code 200 (OK)} e no body o professor, ou com status {@code 404 (Not Found)}.
     */
    
    @GetMapping("/{id}")
    public ResponseEntity<Professor> getProfessor(@PathVariable Long id) {
        log.debug("REST request to get Professor : {}", id);
        Optional<Professor> professor = professorService.findOne(id);
        if(professor.isPresent()) {
            return ResponseEntity.ok().body(professor.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Professor>> getProfessores(){
        List<Professor> lista = professorService.findAllList();
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
     * ou com status {@code 400 (Bad Request)} se o pessoa não é válido,
     * ou com status {@code 500 (Internal Server Error)} se o pessoa não pode ser atualizado.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    
    @PutMapping("/")
    public ResponseEntity<Professor> updateProfessor(@RequestBody Professor professor) throws URISyntaxException {
        log.debug("REST request to update Professor : {}", professor);
        if (professor.getId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid Professor id null");
        }
        Professor result =professorService.save(professor);
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
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) throws URISyntaxException {
        log.debug("REST request to save Professor : {}", professor);
        if (professor.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Um novo professor não pode terum ID");
        }
        Professor result = professorService.save(professor);
        return ResponseEntity.created(new URI("/api/professores/" + result.getId()))
                .body(result);
    }

    @PostMapping(value = "/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Professor> upload(@RequestPart("data") MultipartFile csv) throws IOException {
        List<Professor> savedNotes = new ArrayList<>();
        List<Professor> notes = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(csv).getInputStream(), StandardCharsets.UTF_8)).lines()
                .map(Professor::parseNote).collect(Collectors.toList());
        professorService.saveAll(notes).forEach(savedNotes::add);
        return savedNotes;
    }

    /**
     * {@code DELETE  /:id} : delete pelo "id" professor.
     *
     * @param id o id do pessoas que será delete.
     * @return o {@link ResponseEntity} com status {@code 204 (NO_CONTENT)}.
     */
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        log.debug("REST request to delete Professor : {}", id);

        professorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

