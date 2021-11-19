
package com.trabalho.demo.web.web.api;

import com.trabalho.demo.domain.Professor;
import com.trabalho.demo.service.ProfessorService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabalho.demo.domain.Professor;
import com.trabalho.demo.service.ProfessorService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(tags = "Recursos de Teste para professor")
public class ProfessorTesteResource {
    private final Logger log = LoggerFactory.getLogger(ProfessorTesteResource.class);

    private final ProfessorService professorService;

    public ProfessorTesteResource(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(path = "/professores/criar/{name}")
    public String helloApp(@PathVariable String name) {
        Professor p = new Professor();
        p.setNome(name);
        professorService.save(p);
        return  "criou";
    }

    @GetMapping(path = "/professores/listar/{id}")
    public Professor helloApp(@PathVariable Long id) {
        return  professorService.findOne(id).get();
    }


}

