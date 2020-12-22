package br.edu.ifsul.lpoo.cs.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_tipoarma")

public class TipoArma implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_tipoarma", sequenceName = "seq_tipoarma_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_tipoarma", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    @Column(precision = 2, nullable = false)
    private Float peso;
    
    public TipoArma(){//construtor
        
    } 
    //getter e setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }
    @Override
    public String toString() {
        return nome;
    }
    
}
