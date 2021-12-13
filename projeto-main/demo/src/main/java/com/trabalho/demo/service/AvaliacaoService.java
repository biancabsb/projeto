
package com.trabalho.demo.service;


import com.trabalho.demo.domain.Avaliacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.trabalho.demo.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    private final Logger log = LoggerFactory.getLogger(AvaliacaoService.class);

    private final AvaliacaoRepository AvaliacaoRepository;

    public AvaliacaoService(AvaliacaoRepository AvaliacaoRepository) {
        this.AvaliacaoRepository = AvaliacaoRepository;
    }

    public List<Avaliacao> findAllList(){
        log.debug("Request to get All Avaliacao");
        return AvaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> findOne(Long id) {
        log.debug("Request to get Avaliacao : {}", id);
        return AvaliacaoRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete Avaliacao : {}", id);
        AvaliacaoRepository.deleteById(id);
    }

    public Avaliacao save(Avaliacao avaliacao) {
        log.debug("Request to save Avaliacao : {}", avaliacao);
        avaliacao = AvaliacaoRepository.save(avaliacao);
        return avaliacao;
    }

    public List<Avaliacao> saveAll(List<Avaliacao> avaliacoes) {
        log.debug("Request to save Avaliacao : {}", avaliacoes);
        avaliacoes = AvaliacaoRepository.saveAll(avaliacoes);
        return avaliacoes;
    }
}

