
package br.edu.ifsul.cs;

import br.edu.ifsul.lpoo.cs.gui.CSJFrame;
import br.edu.ifsul.lpoo.cs.gui.JMenuBarHome;
import br.edu.ifsul.lpoo.cs.gui.JPanelHome;
import br.edu.ifsul.lpoo.cs.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.lpoo.cs.gui.usuario.JPanelJogador;
import br.edu.ifsul.lpoo.cs.model.Jogador;
import br.edu.ifsul.lpoo.cs.model.dao.PersistenciaJDBC;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateu
 */
public class Controle {
    
    private PersistenciaJDBC conexaoJDBC;
    private JPanelAutenticacao telaAut;
    private CSJFrame frame;  
    private JMenuBarHome menuBar;
    private JPanelHome telaHome;
    private JPanelJogador telaJogador;
    
    public Controle(){
        
    }
    
    public void initComponents(){
        
        frame = new CSJFrame(this);//inicializa e passa como parâmetro a instância de Controle
            
        telaAut = new JPanelAutenticacao(this);
        telaHome = new JPanelHome(this);
        menuBar = new JMenuBarHome(this);
        telaJogador = new JPanelJogador(this);
                
        frame.addTela(telaAut, "tela_aut");
        frame.addTela(telaHome, "tela_home");
        frame.addTela(telaJogador, "tela_usuario");
        
        frame.showTela("tela_aut");
        frame.setVisible(true); // torna visível o jframe
        
    }
        
     public void showTela(String nomeTela){

        frame.showTela(nomeTela);

    }
    
    public boolean conectarBD(){
        
        conexaoJDBC = new PersistenciaJDBC();
        
        if(getConexaoJDBC() != null)
            return getConexaoJDBC().conexaoAberta();
        
        return false;
    }
    
    public void fecharBD(){
        System.out.println("Fechando conexao com o bd ...");
        getConexaoJDBC().fecharConexao();        
    }
    
    public void autenticar(String nickname, String senha) {
     
        //Atividade 1 - implementar a autenticacao atraves do método doLogin da classe PerssitenciaJDBC (19/11)
       
        try{     
            Jogador j = getConexaoJDBC().doLogin(nickname, senha);
           if(j != null){      
               //System.out.println("jogador: " + j.getNickname());
                frame.showTela("tela_home");
                frame.setJMenuBar(menuBar);        
            }else{                
                  JOptionPane.showMessageDialog(telaAut, "Dados inválidos !", "Autenticação", JOptionPane.INFORMATION_MESSAGE);                
            }
        }catch(Exception e){                      
              JOptionPane.showMessageDialog(telaAut, "Erro ao autenticar -:"+e.getLocalizedMessage(), "Autenticação", JOptionPane.ERROR_MESSAGE);
        }                    
        
    }

    public PersistenciaJDBC getConexaoJDBC() {
        return conexaoJDBC;
    }
    
    
    
}
