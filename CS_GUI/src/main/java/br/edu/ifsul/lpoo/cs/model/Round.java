package br.edu.ifsul.lpoo.cs.model;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_round")

public class Round implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_round", sequenceName = "seq_round_numero", allocationSize = 1)
    @GeneratedValue(generator = "seq_round", strategy = GenerationType.SEQUENCE)
    private Integer numero;  
    
    @Column(nullable = false, length = 200)
    private String descricao;
    
    @Column(nullable = false, length = 200)
    private Boolean status;
    
    @Column(precision = 2, nullable = false)
    private Float premiacao;  
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar data_abertura;
    
    @Column(nullable = false, length = 200)
    private Integer tentativas; 
    
    @ManyToMany
    @JoinTable(name = "tb_round_objetivo", joinColumns = {
        @JoinColumn(name = "round_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "objetivo_id")})
    private List<Objetivo> objetivo;


    
    public Round(){//construtor
        
    }    
    //getter e setter

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Float getPremiacao() {
        return premiacao;
    }

    public void setPremiacao(Float premiacao) {
        this.premiacao = premiacao;
    }

    public Calendar getData_abertura() {
        return data_abertura;
    }

    public void setData_abertura(Calendar data_abertura) {
        this.data_abertura = data_abertura;
    }

    public Integer getTentativas() {
        return tentativas;
    }

    public void setTentativas(Integer tentativas) {
        this.tentativas = tentativas;
    }

    public List<Objetivo> getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(List<Objetivo> objetivo) {
        this.objetivo = objetivo;
    }

    
}