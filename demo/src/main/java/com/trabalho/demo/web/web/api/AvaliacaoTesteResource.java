
package com.trabalho.demo.web.web.api;



import com.trabalho.demo.domain.Avaliacao;
import com.trabalho.demo.service.AvaliacaoService;
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

@RestController
@RequestMapping("/")
@Api(tags = "Recursos de Teste para avaliacao")
public class AvaliacaoTesteResource {
    private final Logger log = LoggerFactory.getLogger(AvaliacaoTesteResource.class);

    private final AvaliacaoService avaliacaoService;

    public AvaliacaoTesteResource(AvaliacaoService avaliacaoService) {
        this.avaliacaoService = avaliacaoService;
    }

    @GetMapping(path = "/avaliacoes/criar/{name}")
    public String helloApp(@PathVariable String name) {
        Avaliacao p = new Avaliacao();
        p.setTitulo(name);
        avaliacaoService.save(p);
        return  "criou";
    }

    @GetMapping(path = "/avaliacoes/listar/{id}")
    public Avaliacao helloApp(@PathVariable Long id) {
        return  avaliacaoService.findOne(id).get();
    }


}
