package br.edu.ifsul.lpoo.cs.gui.usuario;
import br.edu.ifsul.lpoo.cs.gui.usuario.JPanelJogador;
import br.edu.ifsul.cs.Controle;
import br.edu.ifsul.lpoo.cs.model.Arma;
import br.edu.ifsul.lpoo.cs.model.Jogador;
import br.edu.ifsul.lpoo.cs.model.dao.ModeloPersistencia;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JPanelListagem extends javax.swing.JPanel {
    
    private JPanelJogador pnlJogador;
    private ModeloPersistencia persistencia;
    private List<Arma> listarma;
    
    public JPanelListagem(JPanelJogador pnlJogador) {
        this.pnlJogador = pnlJogador;
        initComponents();
    }
 
    public void populaTable(){
        
        DefaultTableModel model =  (DefaultTableModel) jtblTabela.getModel();//recuperacao do modelo da tabela
        
        model.setRowCount(0);//elimina as linhas existentes
        
        try {
            listarma = pnlJogador.getControle().getConexaoJDBC().listArma();
            if(listarma != null){
                if(!listarma.isEmpty()){ 
                    for(Arma a : listarma){
                       //preenche as linhas da table com os dados das armas recuperadas do banco de dados
                        model.addRow(new Object[]{a.getId(), a.getDescricao(), a.getValor(), a.getCalibre(), a.getTipoarma().getId(), a.getMunicao().getId()});
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum registro encontrado!", "Registros", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao recuperar a lista de Funcionarios", "Banco de dados", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(this, "Erro ao listar arma -:"+ex.getLocalizedMessage(), "Arma", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
        
    }
    
    private Arma getArma(int cod){
        
        for(Arma a : listarma){
               if(a.getId() == cod){
                   return a;
               }
        }
        return null;
    }
    //funcao para remover arma atraves da persistencia
    public boolean removeArma(Arma a) throws Exception {
        try {
            persistencia = pnlJogador.getControle().getConexaoJDBC();
            if(persistencia.conexaoAberta()) {
                System.out.println("Abriu conexao via JDBC");
                persistencia.remover(a);
                System.out.println("Fechou conexao via JDBC");
                return true;
            }else{
                System.out.println("Nao conseguiu abrir conexao via jdbc");
            }
            return false;
        } catch(Exception e){
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCentro = new javax.swing.JPanel();
        jspRolagem = new javax.swing.JScrollPane();
        jtblTabela = new javax.swing.JTable();
        pnlSul = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        pnlCentro.setLayout(new java.awt.BorderLayout());

        jtblTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Calibre", "Descricao", "valor", "Municao", "Tipo Arma"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jspRolagem.setViewportView(jtblTabela);

        pnlCentro.add(jspRolagem, java.awt.BorderLayout.CENTER);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        pnlSul.add(btnEditar);

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        pnlSul.add(btnNovo);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        pnlSul.add(btnExcluir);

        pnlCentro.add(pnlSul, java.awt.BorderLayout.PAGE_END);

        add(pnlCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed

        int indice = jtblTabela.getSelectedRow();//recupera a linha selecionada
        if(indice > -1){

            DefaultTableModel model =  (DefaultTableModel) jtblTabela.getModel(); //recuperacao do modelo da table

            Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

            int cod = (int)linha.get(0);
            Arma a = getArma(cod);

            pnlJogador.getEdicao().populaFormulario(a);
            pnlJogador.showTela("tela_edicao");

        }else{
            JOptionPane.showMessageDialog(this, "Selecione uma linha para editar!", "Listagem", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        pnlJogador.getEdicao().populaComboTipoArma();//preenche o combobox do TipoArma                            
        pnlJogador.getEdicao().populaComboMunicao();//preenche o combobox da municao
        pnlJogador.getEdicao().populaFormulario(null); //passa nul para funcao limpar o formulario
        pnlJogador.showTela("tela_edicao");
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int indice = jtblTabela.getSelectedRow();//recupera a linha selecionada
        if(indice > -1){

            DefaultTableModel model =  (DefaultTableModel) jtblTabela.getModel(); //recuperacao do modelo da table

            Vector linha = (Vector) model.getDataVector().get(indice);//recupera o vetor de dados da linha selecionada

            int cod = (int) linha.get(0);
            Arma a = getArma(cod);

            int certeza = JOptionPane.showConfirmDialog(this, "Você tem certeza que deseja remover a arma?", "Remoção", JOptionPane.WARNING_MESSAGE);
            if (certeza == 0){
                    boolean remove;
                    try {
                        remove = removeArma(a);
                        if (remove) {
                            JOptionPane.showMessageDialog(this, "Arma removida com sucesso", "Remoção", JOptionPane.INFORMATION_MESSAGE);
                            populaTable();
                        } else {
                            JOptionPane.showMessageDialog(this, "ERRO AO REMOVER ARMA", "Remoção", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(JPanelEdicao.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, "ERRO AO REMOVER ARMA", "Remoção", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

        }else{
            JOptionPane.showMessageDialog(this, "Selecione uma linha para excluir!", "Listagem", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JScrollPane jspRolagem;
    private javax.swing.JTable jtblTabela;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlSul;
    // End of variables declaration//GEN-END:variables
}
