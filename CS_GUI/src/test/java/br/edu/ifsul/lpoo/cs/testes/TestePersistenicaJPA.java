/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.cs.testes;

import br.edu.ifsul.lpoo.cs.model.dao.PersistenciaJPA;
import org.junit.Test;

/**
 *
 * @author Mateu
 */
public class TestePersistenicaJPA {     
   @Test
     public void testarConexao() throws Exception {        
        PersistenciaJPA persistencia = new PersistenciaJPA();
        if(persistencia.conexaoAberta()){
            
            System.out.println("Conexao com o BD aberta utilizando JPA");
                        
            persistencia.fecharConexao();
            
            System.out.println("Fechou Conexao com o BD aberta utilizando JPA");
            
        }else{
            System.out.println("Não abriu conexao via jpa");
        }        
    }
}
