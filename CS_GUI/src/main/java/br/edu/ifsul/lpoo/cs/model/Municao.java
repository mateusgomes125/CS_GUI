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
@Table(name = "tb_municao")

public class Municao implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_municao", sequenceName = "seq_municao_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_municao", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(precision = 2, nullable = false)
    private Float valor;
    
    public Municao(){//construtor
        
    } 
    //getter e setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
    @Override
    public String toString() {
        return descricao;
    }
    
}
