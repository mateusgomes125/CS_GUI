/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.lpoo.cs.gui.autenticacao;

import br.edu.ifsul.gui.util.NumeroLetra;
import br.edu.ifsul.gui.util.SoNumeros;
import br.edu.ifsul.cs.Controle;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class JPanelAutenticacao extends JPanel implements ActionListener{
    private Controle ctrl;
    
    private GridBagLayout gridLayout;
    
    private JLabel lblLogin;
    private JTextField txfLogin;
    private JLabel lblSenha;
    private JPasswordField psfSenha;
    private JButton btnLogar;
    
    private GridBagConstraints posicionador;
    
    public JPanelAutenticacao(Controle param){
        
        this.ctrl = param;
        initComponents();
    }
    
    private void initComponents(){
        
        gridLayout = new GridBagLayout();
        this.setLayout(gridLayout);
        
        lblLogin = new JLabel("Login: ");
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;//policao da linha (vertical)
        posicionador.gridx = 0;// posição da coluna (horizontal)
        
        
        posicionador.insets = new Insets(0, 0, 0, 0);//espaçamentos entre as linhas e colunas
        
        this.add(lblLogin, posicionador);//o add adiciona o rotulo no painel
        
        
        txfLogin = new JTextField(20);// o 10 significa o numero de colunas da caixa de texto.
        posicionador = new GridBagConstraints();
        posicionador.gridy = 0;// mas na mesma linha que o rótulo
        posicionador.gridx = 1;// a caixa de texto vai para a segunda coluna
        txfLogin.setDocument(new NumeroLetra());
        this.add(txfLogin, posicionador);
        
        
        lblSenha = new JLabel("Senha: ");//
        posicionador = new GridBagConstraints();        
        posicionador.gridy = 1;// linha
        posicionador.gridx = 0;// coluna
        this.add(lblSenha, posicionador);
        
        psfSenha = new JPasswordField(20);//
        posicionador = new GridBagConstraints();        
        posicionador.gridy = 1;// linha
        posicionador.gridx = 1;// coluna
        psfSenha.setDocument(new SoNumeros());
        this.add(psfSenha, posicionador);
        
        btnLogar = new JButton("Autenticar");//
        posicionador = new GridBagConstraints();        
        posicionador.gridy = 2;// linha
        posicionador.gridx = 1;// coluna
        this.add(btnLogar, posicionador);
        btnLogar.setActionCommand("comando_logar");
        btnLogar.addActionListener(this);
                
    }

   
    public void actionPerformed(ActionEvent e) {
        
        //validacao do formulário - ok 
        if(e.getActionCommand().equals(btnLogar.getActionCommand())){
            if(txfLogin.getText().trim().length() == 5 && new String(psfSenha.getPassword()).trim().length() == 3){
                
                ctrl.autenticar(txfLogin.getText().trim(), new String(psfSenha.getPassword()).trim());
                
            }else{
        
                JOptionPane.showMessageDialog(this, "Informe os dados para Login e Senha!", "Autenticação", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
}
