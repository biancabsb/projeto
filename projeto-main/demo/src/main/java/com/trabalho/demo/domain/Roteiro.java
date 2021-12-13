
package com.trabalho.demo.domain;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
@Entity(name = "table_roteiro")

public class Roteiro {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRoteiro;

    @Column(name = "assunto", length = 45, nullable= false)
     private String assunto;
    @Column(name = "conteudo", length = 1000, nullable= false)
     private String conteudo;
    @OneToMany(mappedBy = "Roteiro")
    private List<Pecas> peca;

    
    public static Roteiro parseNote(String line) {
        String[] text = line.split(",");
        Roteiro note = new Roteiro();
        note.setIdRoteiro(Long.parseLong(text[0]));
        return note;
    }
}



