/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.trabalho.demo.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_turma")

public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTurma;
    @Column(name = "nome", length = 64, nullable = false)
    private String nome;
    
    @ManyToOne
    private Professor Professor;
    
    @ManyToMany
    @JoinTable(name="tbTurma_has_tbAluno",
             joinColumns={@JoinColumn(name="id_turma")},
             inverseJoinColumns={@JoinColumn(name="id_alunos")})
    private List<Aluno> alunos;
}

