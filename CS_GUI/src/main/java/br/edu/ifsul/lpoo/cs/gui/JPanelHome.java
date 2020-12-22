/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.cs.gui;

import br.edu.ifsul.cs.Controle;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *

 */
public class JPanelHome extends JPanel{
    private JLabel lblMensagem;
    private JLabel lblData;
    private BorderLayout layoutGeo;
    private Controle ctrl;
    
    public JPanelHome(Controle param){
        this.ctrl = param;
        initComponents();
    }
    
    private void initComponents(){
        lblMensagem = new JLabel("Tela de Entrada");
        this.setLayout(layoutGeo);
        this.add(lblMensagem, BorderLayout.NORTH);
    }
}
