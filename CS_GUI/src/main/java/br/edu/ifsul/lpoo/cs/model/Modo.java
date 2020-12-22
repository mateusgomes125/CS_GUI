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
@Table(name = "tb_modo")

public class Modo implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_modo", sequenceName = "seq_modo_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_modo", strategy = GenerationType.SEQUENCE)
    private Integer id;  
    
    @Column(nullable = false, length = 200)
    private String nome;   
    
    @Column(nullable = false, length = 200)
    private String descricao;  
    
    @Column(nullable = false, length = 200)
    private Integer nivel;   
    
    public Modo(){//construtor
        
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
    
}
