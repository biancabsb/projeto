
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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "table_pecas")

public class Pecas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long idPecas;
    @Column(name = "sentido", length = 45,nullable= false)
     private String sentido;
    
    @ManyToOne
    private Roteiro Roteiro;
    
   @OneToMany(mappedBy = "Pecas")
    private List<Parte> partes;

   
    public static Pecas parseNote(String line) {
        String[] text = line.split(",");
        Pecas note = new Pecas();
        note.setIdPecas(Long.parseLong(text[0]));
        return note;
    }
    
}
