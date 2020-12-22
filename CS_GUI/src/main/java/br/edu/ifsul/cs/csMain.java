
package br.edu.ifsul.cs;

import br.edu.ifsul.lpoo.cs.gui.CSJFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateu
 * @observação: Classe que contem o método main, para iniciar a execução.
 */
public class csMain {
            
    public Controle ctrl = null;
    
    public csMain(){
        
         ctrl = new Controle();
         if(ctrl.conectarBD()){
             
            JOptionPane.showMessageDialog(null, "Conectou no Banco de Dados!", "Banco de Dados", JOptionPane.INFORMATION_MESSAGE);
            ctrl.initComponents();
        }else{
            JOptionPane.showMessageDialog(null, "Não conectou no Banco de Dados!", "Banco de Dados", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //primeiro método que será executado
    public static void main(String[] args){
        
        //cria uma instância desse classe (chama o construtor)
        new csMain();
    }
    
}
