/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trabalho.demo.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_aluno")

public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    @Column(name = "nome", length = 64, nullable = false)
    private String nome;
    @Column(name = "dataNascimento", nullable = false)
    private Instant dataNascimento;
    @Column(nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String telefone;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String endereco;
    
    @OneToOne
    private Avaliacao avaliacao;
     
    public static Aluno parseNote(String line) {
        String[] text = line.split(",");
        Aluno note = new Aluno();
        note.setMatricula(Long.parseLong(text[0]));
        note.setNome(text[1]);
        return note;
    }
}
