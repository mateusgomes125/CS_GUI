/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.cs.model.dao;

import br.edu.ifsul.lpoo.cs.model.Arma;
import br.edu.ifsul.lpoo.cs.model.Jogador;
import br.edu.ifsul.lpoo.cs.model.Municao;
import br.edu.ifsul.lpoo.cs.model.TipoArma;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Mateu
 */
public class PersistenciaJPA implements ModeloPersistencia {

    public EntityManagerFactory factory;
    public EntityManager entity;

    public PersistenciaJPA() {
        factory = Persistence.createEntityManagerFactory("pu_db_cs");
        entity = factory.createEntityManager();
    }

     @Override
    public Boolean conexaoAberta() {
        return entity.isOpen();
    }
    @Override
    public void fecharConexao() {
        entity.close();
    }
    @Override
    //throws Exception  - o tratamento do erro deverá ser realizado
    //pela classe que chamar esse metodo
    public Object find(Class c, Object id) throws Exception {        
        return entity.find(c, id);//encontra um determinado registro
    }

    @Override
    public void persist(Object o) throws Exception {
        entity.getTransaction().begin();//abre transacao
        entity.persist(o);//persiste (update ou insert)
        entity.getTransaction().commit();//commit na transacao
    }

    @Override
    public void remover(Object o) throws Exception {        
        entity.remove(o);
    }

    @Override
    public List<Municao> listMunicao() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<TipoArma> listTipoArma() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public List<Arma> listArma() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Jogador doLogin(String nickname, String senha) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
