package br.edu.ifsul.lpoo.cs.model.dao;

import br.edu.ifsul.lpoo.cs.model.Arma;
import br.edu.ifsul.lpoo.cs.model.Jogador;
import br.edu.ifsul.lpoo.cs.model.Municao;
import br.edu.ifsul.lpoo.cs.model.TipoArma;
import java.util.List;

  





public interface ModeloPersistencia {

  public Boolean conexaoAberta();//protótipos
    public void fecharConexao();
    public Object find(Class c, Object id) throws Exception;
    public void persist(Object o) throws Exception;
    public void remover(Object o) throws Exception;
    public List<Municao> listMunicao() throws Exception;
    public List<TipoArma> listTipoArma() throws Exception;
    public List<Arma> listArma() throws Exception;
    public Jogador doLogin(String nickname, String senha) throws Exception;


}