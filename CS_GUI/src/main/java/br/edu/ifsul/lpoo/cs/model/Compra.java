package br.edu.ifsul.lpoo.cs.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_compra")

public class Compra implements Serializable{
    @Id
    @SequenceGenerator(name = "seq_compra", sequenceName = "seq_compra_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_compra", strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datanascimento;
    
    @Column(precision = 2, nullable = false)
    private Float valor;
    
    @ManyToOne
    @JoinColumn(name = "jogador_nickname", nullable = false)
    private Jogador jogador;
    
   @ManyToMany
    @JoinTable(name = "tb_compra_municao", joinColumns = {
        @JoinColumn(name = "compra_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "municao_id")})
    private List<Municao> municao;

    @ManyToMany
    @JoinTable(name = "tb_compra_arma", joinColumns = {
        @JoinColumn(name = "compra_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "arma_id")})
    private List<Arma> arma;
    
    public Compra(){//construtor
        
    } 
    //getter e setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Calendar datanascimento) {
        this.datanascimento = datanascimento;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public List<Municao> getMunicao() {
        return municao;
    }

   public void addArma(Municao municao){
        this.municao.add(municao);
    }

    public List<Arma> getArma() {
        return arma;
    }

    public void addArma(Arma arma){
        this.arma.add(arma);
    }

    
    
}