
package br.edu.ifsul.lpoo.cs.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_jogador")
public class Jogador implements Serializable{    
    
    @Column(nullable = false, length = 200)
    @Id
    private String nickname;  
    
    @Column(precision = 2, nullable = false)
    private Float saldo; 
    
    @Column(nullable = false, length = 200)
    private String senha;    
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar datanascimento;
    
    @ManyToOne
    @JoinColumn(name = "patente_id", nullable = false)
    private Patente patente;
    
    public Jogador(){//construtor
        
    }    
    //getter e setter

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Calendar getDatanascimento() {
        return datanascimento;
    }

    public void setDatanascimento(Calendar datanascimento) {
        this.datanascimento = datanascimento;
    }

    public Patente getPatente() {
        return patente;
    }

    public void setPatente(Patente patente) {
        this.patente = patente;
    }
    

}
