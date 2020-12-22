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
@Table(name = "tb_objetivo")

class Objetivo implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_objetivo", sequenceName = "seq_objetivo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_objetivo", strategy = GenerationType.SEQUENCE)
    private Integer id;     

    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(nullable = false, length = 200)
    private String nome;
    
    public Objetivo(){//construtor
        
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
