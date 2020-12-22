package br.edu.ifsul.lpoo.cs.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "tb_arma")

public class Arma implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_arma", sequenceName = "seq_arma_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_arma", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(precision = 2, nullable = false)
    private Float valor;
    
    @Column(precision = 2, nullable = false)
    private Float calibre;
    
    @ManyToOne
    @JoinColumn(name = "tipoarma_id", nullable = false)
    private TipoArma tipoarma;
    
    @ManyToOne
    @JoinColumn(name = "municao_id", nullable = false)
    private Municao municao;
    
    public Arma(){//construtor
        
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

    public Float getCalibre() {
        return calibre;
    }

    public void setCalibre(Float calibre) {
        this.calibre = calibre;
    }

    public TipoArma getTipoarma() {
        return tipoarma;
    }

    public void setTipoarma(TipoArma tipoarma) {
        this.tipoarma = tipoarma;
    }

    public Municao getMunicao() {
        return municao;
    }

    public void setMunicao(Municao municao) {
        this.municao = municao;
    }
    
}
