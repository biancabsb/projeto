
package com.trabalho.demo.web.web.api;


import com.trabalho.demo.domain.Pessoa;
import com.trabalho.demo.service.PessoaService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(tags = "Recursos de Teste para pessoa")
public class PessoaTesteResource {
    private final Logger log = LoggerFactory.getLogger(PessoaTesteResource.class);

    private final PessoaService pessoaService;

    public PessoaTesteResource(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping(path = "/pessoas/criar/{name}")
    public String helloApp(@PathVariable String name) {
        Pessoa p = new Pessoa();
        p.setNome(name);
        pessoaService.save(p);
        return  "criou";
    }

    @GetMapping(path = "/pessoas/listar/{id}")
    public Pessoa helloApp(@PathVariable Long id) {
        return  pessoaService.findOne(id).get();
    }


}

