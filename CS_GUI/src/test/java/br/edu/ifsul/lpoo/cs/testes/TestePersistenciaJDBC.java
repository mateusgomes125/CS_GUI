/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.cs.testes;

import br.edu.ifsul.lpoo.cs.model.Arma;
import br.edu.ifsul.lpoo.cs.model.Municao;
import br.edu.ifsul.lpoo.cs.model.TipoArma;
import br.edu.ifsul.lpoo.cs.model.dao.PersistenciaJDBC;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author Mateu
 */
public class TestePersistenciaJDBC {
    //@Test
    public void testePersistenciaMunicao() throws Exception {
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if (persistencia.conexaoAberta()) {
            System.out.println("Abriu conexao JDBC");           
//--------------------------------PERSISTIR--------------------------------------------------------------            
            Municao m = new Municao();
            m.setDescricao("pistola"); //recebendo a descricao
            m.setValor(5.5f);// recebendo o valor
            persistencia.persist(m); //persistindo os dados do objeto no banco na tabela correspondente
            System.out.println("Munição -> Descrição: " + m.getDescricao() + " Valor: " + m.getValor());             
//--------------------------------UPDATE--------------------------------------------------------------            
            Municao m1 = new Municao();
            m1.setId(1);//atribuindo um Id de um registro existente na tabela
            m1.setDescricao("Fuzil"); //recebendo a descricao
            m1.setValor(6.5f);// recebendo o valor
            persistencia.persist(m1); //persistindo os dados do objeto no banco na tabela correspondente
            System.out.println("UPDATE: Munição -> Descrição: " + m1.getDescricao() + " Valor: " + m1.getValor());            
 //--------------------------------FIND-------------------------------------------------------------------
            Municao m2 = (Municao)persistencia.find(Municao.class, new Integer(1)); //criando o objeto Municao
            System.out.println("FIND: Munição -> Descrição: " + m2.getDescricao() + " Valor: " + m2.getValor());           
//--------------------------------LISTAR----------------------------------------------------------------             
            List<Municao> municao = persistencia.listMunicao();            
            System.out.println("\nMostra a lista de Munições:");
            for (Municao mu : municao) {
                System.out.println("\n" 
                        + "Id: " + mu.getId()
                        + "\nDescricao: " + mu.getDescricao()
                        + "\nValor: " + mu.getValor());                
            }
//--------------------------------REMOVER-----------------------------------------------------------------
            Municao m3 = new Municao(); //Instanciando outro objeto do tipo Municao
            m3.setId(2);//atribuindo Id de um registro existente na tabela
            persistencia.remover(m3);// fazendo a remorção do registro referente ao id atribuido            
        } else {
            System.out.println("Nao abriu conexao JDBC");
        }
    }
    
    
    //@Test
    public void testePersistenciaTipoArma() throws Exception {
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if (persistencia.conexaoAberta()) {
            System.out.println("Abriu conexao JDBC");
//--------------------------------PERSISTIR--------------------------------------------------------------            
            TipoArma tp = new TipoArma(); // criando objeto TipoArma tp
            tp.setNome("Fuzil"); // recebendo nome
            tp.setPeso(6.0f);// recebendo peso
            persistencia.persist(tp); //persistindo os dados do objeto no banco na tabela correspondente
            System.out.println("TipoArma -> Nome: " + tp.getNome() + " Peso: " + tp.getPeso() + "kg");  
//--------------------------------UPDATE--------------------------------------------------------------            
            TipoArma tp1 = new TipoArma(); // criando objeto TipoArma tp
            tp1.setId(1);
            tp1.setNome("Metralhadora"); // recebendo nome
            tp1.setPeso(7.0f);// recebendo peso
            persistencia.persist(tp1); //persistindo os dados do objeto no banco na tabela correspondente
            System.out.println("UPDATE: TipoArma -> Nome: " + tp1.getNome() + " Peso: " + tp1.getPeso() + "kg");                          
//--------------------------------FIND-------------------------------------------------------------------            
            TipoArma tp2 = (TipoArma) persistencia.find(TipoArma.class, new Integer(1));//atribuido o retorno do metodo find(objeto TipoArma) para o objeto tp2
            System.out.println("FIND: TipoArma -> Nome: " + tp2.getNome() + " Peso: " + tp2.getPeso() + "kg");                        
//--------------------------------LISTAR----------------------------------------------------------------            
            List<TipoArma> tipoArma = persistencia.listTipoArma();           
            System.out.println("\nMostra a lista de Munições:");
            for (TipoArma ta : tipoArma) {
                System.out.println("\n" 
                        + "Id: " + ta.getId()
                        + "\nNome: " + ta.getNome()
                        + "\nPeso: " + ta.getPeso() + "kg");
            }   
//--------------------------------REMOVER--------------------------------------------------------------        
            TipoArma tp3 = new TipoArma();
            tp3.setId(2);//atribuindo Id de um registro existente na tabela
            persistencia.remover(tp3);// fazendo a remorção do registro referente ao id atribuido
        } else {
            System.out.println("Nao abriu conexao JDBC");
        }
    }
    
    
    //@Test
    public void testePersistenciaArma() throws Exception {
        PersistenciaJDBC persistencia = new PersistenciaJDBC();
        if (persistencia.conexaoAberta()) {
            System.out.println("Abriu conexao JDBC");           
//---------------------------------PERSISTIR-----------------------------------------------------            
            Arma a = new Arma();// criando objeto arma a
            a.setCalibre(5.5f); // recebendo o calibre
            a.setDescricao("Fuzil"); // recebendo descricao
            a.setValor(5000.0f); // recebendo o valor            
            TipoArma tp = (TipoArma) persistencia.find(TipoArma.class, new Integer(1)); // buscando registro na tabela tipoarma e passando para o objeto tp
            a.setTipoarma(tp); // atribuindo o objeto tp ao objeto a           
            Municao m = (Municao) persistencia.find(Municao.class, new Integer(1)); // buscando registro na tabela municao e passando para o objeto m
            a.setMunicao(m);// atribuindo o objeto m ao objeto a
            
            persistencia.persist(a); //persistindo os dados do objeto no banco na tabela correspondente
            System.out.println("Arma -> Descrição: " + a.getDescricao() + " Valor: " + a.getValor());
//---------------------------------UPDATE-----------------------------------------------------            
            Arma a1 = new Arma();// criando objeto arma a
            a1.setId(1);
            a1.setCalibre(5.5f); // recebendo o calibre
            a1.setDescricao("glock"); // recebendo descricao
            a1.setValor(2500.0f); // recebendo o valor            
            TipoArma tp4 = (TipoArma) persistencia.find(TipoArma.class, new Integer(1)); // buscando registro na tabela tipoarma e passando para o objeto tp
            a1.setTipoarma(tp4); // atribuindo o objeto tp ao objeto a           
            Municao m4 = (Municao) persistencia.find(Municao.class, new Integer(1)); // buscando registro na tabela municao e passando para o objeto m
            a1.setMunicao(m4);// atribuindo o objeto m ao objeto a
            
            persistencia.persist(a1); //persistindo os dados do objeto no banco na tabela correspondente
            System.out.println("UPDATE: Arma -> Descrição: " + a1.getDescricao() + " Valor: " + a1.getValor());            
//--------------------------------FIND-------------------------------------------------------------------            
            Arma a2 = (Arma) persistencia.find(Arma.class, new Integer(1));//atribuido o retorno do metodo find(objeto Arma) para o objeto a2
            System.out.println("FIND: Arma -> Descrição: " + a2.getDescricao() + " Valor: " + a2.getValor());
//--------------------------------LISTAR----------------------------------------------------------            
            List<Arma> arma = persistencia.listArma();           
            System.out.println("\nMostra a lista de Munições:");
            for (Arma ar : arma) {
                System.out.println("\n" 
                        + "Id: " + ar.getId()
                        + "\nCalibre: " + ar.getCalibre()
                        + "\nDescrição: " + ar.getDescricao()
                        + "\nValor: " + ar.getValor()
                        + "\nTipoArma_id: " + ar.getTipoarma().getId()
                        + "\nMunicao_id: " + ar.getMunicao().getId());
            }    
                Arma a3 = new Arma();
                a3.setId(2);//atribuindo Id de um registro existente na tabela
                persistencia.remover(a3);// fazendo a remorção do registro referente ao id atribuido
        } else {
            System.out.println("Nao abriu conexao JDBC");
        }
    }

    
}
