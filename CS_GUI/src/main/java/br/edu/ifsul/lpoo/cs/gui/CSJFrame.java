/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.cs.gui;

import br.edu.ifsul.lpoo.cs.gui.autenticacao.JPanelAutenticacao;
import br.edu.ifsul.cs.Controle;
import java.awt.CardLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *

 */
public class CSJFrame extends JFrame implements WindowListener{
     public Controle controle;
    public CardLayout cardLayout;
    public JPanel paineldeFundo;
    
    public CSJFrame(Controle param){
        
        this.controle = param;
        initComponents();
    }
    
    private void initComponents(){
        
        this.setTitle("Counter-Strike: Global Offensive");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );//finaliza o processo quando o frame é fechado.
        
        this.addWindowListener(this);//adiciona o listener no frame
        
        paineldeFundo = new JPanel();
        
        cardLayout = new CardLayout();
        paineldeFundo.setLayout(cardLayout);
        
        this.add(paineldeFundo);
        
    }

    public void addTela(JPanel painel, String nome){        
        paineldeFundo.add(painel, nome);
    }
    
    public void showTela(String nome){
        cardLayout.show(paineldeFundo, nome);
    }
    
    @Override
    public void windowClosing(WindowEvent e) {
    
        controle.fecharBD();//fecha a conexao antes de fechar o jframe
    }
    

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
       
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
       
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        
    }

    
}
