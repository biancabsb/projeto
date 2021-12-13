
package com.trabalho.demo.domain;


import java.io.Serializable;
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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_avaliacao")
public class Avaliacao  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvaliacao;

    @Column(name = "nome", length = 64)
    private String titulo;

    private String subtitulo;
    private String roteiroConteudo;
    private String instrucoes;
    private Long Nquestoes;
    private Long valor;
    private Long tempo;
    private Long Ntentativas;
    private Boolean isActive;

    @ManyToMany
    @JoinTable(name="tbAvaliacao_has_tbRoteiro", joinColumns=
    {@JoinColumn(name="idAvaliacao")}, inverseJoinColumns=
      {@JoinColumn(name="idRoteiro")})
    private List<Roteiro> roteiros;


    public static Avaliacao parseNote(String line) {
        String[] text = line.split(",");
        Avaliacao note = new Avaliacao();
        note.setIdAvaliacao(Long.parseLong(text[0]));
        note.setTitulo(text[1]);
        return note;
    }
}

