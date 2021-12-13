
package com.trabalho.demo.domain;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
@Entity(name = "table_parte")

public class Parte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idParte;

    @Column(name = "conteudo", length = 45, nullable= false)
    private String conteudo;
    @Column(nullable= false)
    private int numero;
    @Column(name = "nome", length = 45,nullable= false)
    private String nome;
    
    @ManyToOne
    private Pecas Pecas;
    @ManyToOne
    private Roteiro Roteiro;
    
   
    public static Parte parseNote(String line) {
        String[] text = line.split(",");
        Parte note = new Parte();
        note.setIdParte(Long.parseLong(text[0]));
        return note;
    }
}
