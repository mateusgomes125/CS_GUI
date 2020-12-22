/*
 * 
 */
package br.edu.ifsul.lpoo.cs.gui;

import br.edu.ifsul.cs.Controle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class JMenuBarHome extends JMenuBar implements ActionListener {
    
    private JMenu menuArquivo;
    private JMenuItem menuItemSair;
    
    private JMenu menuCadastro;
    private JMenuItem menuItemUsuario;    
    
    private Controle ctrl;
    
    public JMenuBarHome(Controle param){
        this.ctrl = param;
        initComponents();
    }
    
    private void initComponents(){
        
        menuArquivo = new JMenu("Arquivo");                
            menuItemSair = new JMenuItem("Sair");
            menuItemSair.setActionCommand("menu_sair");
            menuItemSair.addActionListener(this);        
        menuArquivo.add(menuItemSair);
        
        
        menuCadastro = new JMenu("Cadastros");                
            menuItemUsuario = new JMenuItem("Arma");
            menuItemUsuario.setActionCommand("menu_usuario");
            menuItemUsuario.addActionListener(this);        
        menuCadastro.add(menuItemUsuario);
        
        this.add(menuArquivo);
        this.add(menuCadastro);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getActionCommand().equals(menuItemSair.getActionCommand())){
            
            int d = JOptionPane.showConfirmDialog(this, "Deseja realmente sair do sistema? ", "Sair", JOptionPane.YES_NO_OPTION);
            if(d == 0){                
                ctrl.fecharBD();
                System.exit(0);
            }
            
        }else if(e.getActionCommand().equals(menuItemUsuario.getActionCommand())){
            
            ctrl.showTela("tela_usuario");
            
        }
    }
    
}
