/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.cs.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import br.edu.ifsul.lpoo.cs.model.Arma;
import br.edu.ifsul.lpoo.cs.model.Jogador;
import br.edu.ifsul.lpoo.cs.model.Municao;
import br.edu.ifsul.lpoo.cs.model.TipoArma;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateu
 */
public class PersistenciaJDBC implements ModeloPersistencia {

    private final String DRIVER = "org.postgresql.Driver";
    private final String USER = "postgres";
    private final String SENHA = "123";
    public static final String URL = "jdbc:postgresql://localhost:5432/db_cs";
    private Connection con = null;
    
    public PersistenciaJDBC(){
        
        try{           
            Class.forName(DRIVER); //carregamento do driver postgresql em tempo de execução 
            System.out.println("Tentando estabelecer conexao com : "+URL);
            this.con = (Connection) DriverManager.getConnection(URL, USER, SENHA);                                      
        }catch(ClassNotFoundException | SQLException e){           
            e.printStackTrace();                   
        }
    }

    @Override
    public Boolean conexaoAberta() {
       try {
            return !con.isClosed();//verifica se a conexao está aberta
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void fecharConexao() {
        try{                               
            this.con.close();//fecha a conexao.            
        }catch(SQLException e){            
            e.printStackTrace();
        }
    }

    @Override
    public Object find(Class c, Object id) throws Exception {
        if(c == Municao.class){//ENCONTRAR REGISTRO NA TABELA MUNIÇÃO
            try{
               PreparedStatement ps = this.con.prepareStatement("select id, descricao, valor from tb_municao "
               + "where id=?"); // armazenando comando sql
               ps.setInt(1, new Integer(id.toString())); // refere-se ao parametro 1
               ResultSet rs = ps.executeQuery();// executando o comando sql
               if(rs.next()){
                   Municao m = new Municao();
                   
                   m.setId(rs.getInt("id"));
                   m.setDescricao(rs.getString("descricao"));
                   m.setValor(rs.getFloat("valor"));
                   
                   return m;
               }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(c == TipoArma.class){//Encontrar regiatro na tabela TipoArma
            try{
               PreparedStatement ps = this.con.prepareStatement("select id, nome, peso from tb_tipoarma "
               + "where id=?"); // armazenando comando sql
               ps.setInt(1, new Integer(id.toString())); // refere-se ao parametro 1
               ResultSet rs = ps.executeQuery(); // executando o comando sql
               if(rs.next()){
                   TipoArma tp = new TipoArma();
                   
                   tp.setId(rs.getInt("id"));
                   tp.setNome(rs.getString("nome"));
                   tp.setPeso(rs.getFloat("peso"));
                   
                   return tp;
               }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       if(c == Arma.class){//Encontrar regiatro na tabela Arma
           try {
                PreparedStatement ps = this.con.prepareStatement("select id, descricao, valor, calibre, municao_id, tipoarma_id "
                + "from tb_arma where id = ?"); // armazenando comando sql
                ps.setInt(1, new Integer(id.toString()));// refere-se ao parametro 1
                ResultSet rs = ps.executeQuery();// executando o comando sql
                if (rs.next()) {
                   Arma a = new Arma();
                   TipoArma tp = new TipoArma();
                   Municao m = new Municao();     
                   
                   a.setId(rs.getInt("id"));
                   a.setCalibre(rs.getFloat("calibre"));
                   a.setDescricao(rs.getString("descricao"));   
                   a.setValor(rs.getFloat("valor"));   
                   
                   
                   tp.setId(rs.getInt("tipoarma_id"));
                   
                   m.setId(rs.getInt("municao_id"));                  
                   
                   a.setTipoarma(tp);
                   a.setMunicao(m);                   
                    return a;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
       } 
       return null;
    }
    

    @Override
    public void persist(Object o) throws Exception {
  
            if (o instanceof TipoArma) {//Persistencia na tabela tipoarma
            TipoArma tp = (TipoArma) o;// atribuição do objeto recebido pelo método
            if (tp.getId() == null) {//se não houver registro na tabela
                PreparedStatement ps = this.con.prepareStatement("insert into tb_tipoarma (id, nome, peso) "
                        + "values (nextval('seq_tipoarma_id'),?,?) returning id");//armazenando o comando
                ps.setString(1, tp.getNome());// parametro 2
                ps.setFloat(2, tp.getPeso());// parametro 3
                ResultSet rs = ps.executeQuery(); // executa o comando sql
                if (rs.next()) {
                    tp.setId(rs.getInt("id"));
                }
            }
            else{//se já houver registro na tabela
                   PreparedStatement ps = this.con.prepareStatement("update tb_tipoarma set nome=?, peso =? where id=?");//armazenando o comando
                   ps.setString(1, tp.getNome());// parametro 1
                   ps.setFloat(2,tp.getPeso());// parametro 2
                   ps.setInt(3, tp.getId());// parametro 3
                   ps.execute();// executa o comando sql
               }
            }
             
             if (o instanceof Municao) {//Persistencia na tabela municao
            Municao m = (Municao) o;// atribuição do objeto recebido pelo método
            if (m.getId() == null) {//se não houver registro na tabela
                PreparedStatement ps = this.con.prepareStatement("insert into tb_municao (id, descricao, valor) "
                        + "values (nextval('seq_municao_id'),?,?) returning id");//armazenando o comando sql
                ps.setString(1, m.getDescricao()); // parametro 1
                ps.setFloat(2, m.getValor());// parametro 2
                ResultSet rs = ps.executeQuery();// execução do comando sql
                if (rs.next()) {
                    m.setId(rs.getInt("id"));
                }
            }
            else{//se já houver registro na tabela
                   PreparedStatement ps = this.con.prepareStatement("update tb_municao set descricao=?, valor =? where id=?");//armazenando o comando sql
                   ps.setString(1, m.getDescricao());// parametro 1
                   ps.setFloat(2,m.getValor());// parametro 2
                   ps.setInt(3, m.getId());// parametro 3
                   ps.execute();// executa o comando sql
               }
            }
       
           if (o instanceof Arma) {//Persistencia na tabela municao
            Arma a = (Arma) o;// atribuição do objeto recebido pelo método
            if ((Arma) this.find(Arma.class,a.getId()) == null) {//se não houver registro na tabela
                /*PreparedStatement ps = this.con.prepareStatement("insert into tb_arma (id, descricao, valor, calibre, tipoarma_id, municao_id) "
                + "values ( nextval('seq_arma_id'),?,?,?,?,?) returning id");//armazenando o comando sql*/
                PreparedStatement ps = this.con.prepareStatement("insert into tb_arma (id, descricao, valor, calibre, tipoarma_id, municao_id) values (?,?,?,?,?,?)");
                ps.setInt(1, a.getId()); // parametro 1
                ps.setString(2, a.getDescricao()); // parametro 2
                ps.setFloat(3, a.getValor()); // parametro 3
                ps.setFloat(4, a.getCalibre()); // parametro 4
                ps.setInt(5, a.getTipoarma().getId()); // parametro 5
                ps.setInt(6, a.getMunicao().getId()); // parametro 6
                
               ps.execute();
//                ResultSet rs = ps.executeQuery();// executa o comando sql
//                if (rs.next()) {
//                    a.setId(rs.getInt("id"));
//                }
            }
            else{//se já houver registro na tabela
                   PreparedStatement ps = this.con.prepareStatement("update tb_arma set calibre=?, descricao=?, valor=?, "
                   + "municao_id=?, tipoarma_id=? where id=?");//armazenando o comando sql
                   ps.setFloat(1, a.getCalibre()); //parametro 1 
                   ps.setString(2, a.getDescricao()); //parametro 2
                   ps.setFloat(3, a.getValor()); //parametro 3
                   ps.setInt(4, a.getMunicao().getId()); //parametro 4
                   ps.setInt(5, a.getTipoarma().getId());//parametro 5
                   ps.setInt(6, a.getId());//parametro 6
                   ps.execute();// executa o comando sql
               }
            }      
    }
    @Override
    public void remover(Object o) throws Exception {
        try{
             if (o instanceof TipoArma) {
                TipoArma tp = (TipoArma) o; // atribuição do objeto recebido pelo método
                PreparedStatement ps = this.con.prepareStatement("delete from tb_tipoarma where id=?"); //armazenando o comando sql
                ps.setInt(1, tp.getId()); //1 refere-se ao primeiro 
                ps.execute();//execução do comando sql
            }
        }catch (SQLException e) {
                e.printStackTrace();
            }
       try{
           if (o instanceof Municao) {
                Municao m = (Municao) o;// atribuição do objeto recebido pelo método
                PreparedStatement ps = this.con.prepareStatement("delete from tb_municao where id=?");//armazenando o comando sql
                ps.setInt(1, m.getId()); //1 refere-se ao primeiro 
                ps.execute();//execução do comando sql
            }
       }catch (SQLException e) {
                e.printStackTrace();
            }
        try{
            if (o instanceof Arma) {
                Arma a = (Arma) o;// atribuição do objeto recebido pelo método
                PreparedStatement ps = this.con.prepareStatement("delete from tb_arma where id=?");//armazenando o comando sql
                ps.setInt(1, a.getId()); //1 refere-se ao primeiro 
                ps.execute();//execução do comando sql
            }
        }catch (SQLException e) {
                e.printStackTrace();
            }
        
    }
    
    @Override
    public List<Municao> listMunicao() throws Exception {
        PreparedStatement ps = this.con.prepareStatement("select id, descricao, valor from tb_municao "
        + "order by id asc");//armazenando o comando sql
        ResultSet rs = ps.executeQuery();//execução do comando sql
        List<Municao> municao = new ArrayList(); //criando lista para receber os registros

        while (rs.next()) {// percorrendo a tabela
            Municao m = new Municao();// criando objeto auxiliar 
            m.setId(rs.getInt("id"));
            m.setDescricao(rs.getString("descricao"));
            m.setValor(rs.getFloat("valor"));
            municao.add(m); // adicionando os objetos com os registros na lista
        }
        return municao;
    }

    @Override
    public List<TipoArma> listTipoArma() throws Exception {
        PreparedStatement ps = this.con.prepareStatement("select id, nome, peso from tb_tipoarma order by id asc");//armazenando o comando sql
        ResultSet rs = ps.executeQuery();//execução do comando sql
        List<TipoArma> tipoArma = new ArrayList();//criando lista para receber os registros

        while (rs.next()) {// percorrendo a tabela
            TipoArma tp= new TipoArma();// criando objeto auxiliar 
            tp.setId(rs.getInt("id"));
            tp.setNome(rs.getString("nome"));
            tp.setPeso(rs.getFloat("peso"));
            tipoArma.add(tp);
        }
        return tipoArma;
    }
    
    @Override
    public List<Arma> listArma() throws Exception {
        PreparedStatement ps = this.con.prepareStatement("select id, descricao, valor,calibre, municao_id, tipoarma_id "
        + "from tb_arma order by id asc");//armazenando o comando sql
        ResultSet rs = ps.executeQuery();//execução do comando sql
        List<Arma> arma = new ArrayList();//criando lista para receber os registros

        while (rs.next()) { // percorrendo a tabela
            Arma a = new Arma();// criando objeto auxiliar 
            TipoArma tp = new TipoArma();
            Municao m = new Municao();     
            
            a.setId(rs.getInt("id"));
            a.setDescricao(rs.getString("descricao"));
            a.setValor(rs.getFloat("valor"));
            a.setCalibre(rs.getFloat("calibre"));
           
            tp.setId(rs.getInt("tipoarma_id"));
            m.setId(rs.getInt("municao_id"));
            
            a.setTipoarma(tp);
            a.setMunicao(m);
            
            arma.add(a);// adicionando os objetos com os registros na lista
        }
        return arma;
    }
    
    @Override
    public Jogador doLogin(String nickname, String senha) throws Exception {
        
        Jogador j = null;
        PreparedStatement ps = this.con.prepareStatement("select nickname, senha from tb_jogador where nickname = ? and senha = ?");
            ps.setString(1, nickname);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){ //se o retorno (matriz) contiver uma linha
                j = new Jogador();
                j.setNickname(rs.getString("nickname"));
                j.setSenha(rs.getString("senha")); //recupera a informação da coluna id na primeira linha                                        
            }           
            ps.close();
            return j;        
    }
}