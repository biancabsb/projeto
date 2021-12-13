
package com.trabalho.demo.service;



import com.trabalho.demo.domain.Professor;
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
import com.trabalho.demo.repository.ProfessorRepository;

import com.trabalho.demo.domain.Professor;
import com.trabalho.demo.repository.ProfessorRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final Logger log = LoggerFactory.getLogger(ProfessorService.class);

    private final ProfessorRepository ProfessorRepository;

    public ProfessorService(ProfessorRepository ProfessorRepository) {
        this.ProfessorRepository = ProfessorRepository;
    }

    public List<Professor> findAllList(){
        log.debug("Request to get All Professor");
        return ProfessorRepository.findAll();
    }

    public Optional<Professor> findOne(Long id) {
        log.debug("Request to get Professor : {}", id);
        return ProfessorRepository.findById(id);
    }

    public void delete(Long id) {
        log.debug("Request to delete Professor : {}", id);
        ProfessorRepository.deleteById(id);
    }

    public Professor save(Professor professor) {
        log.debug("Request to save Professor : {}", professor);
        professor = ProfessorRepository.save(professor);
        return professor;
    }

    public List<Professor> saveAll(List<Professor> avaliacoes) {
        log.debug("Request to save Professor : {}", avaliacoes);
        avaliacoes = ProfessorRepository.saveAll(avaliacoes);
        return avaliacoes;
    }
}


