
package br.edu.ifsul.lpoo.cs.gui.usuario;

import br.edu.ifsul.gui.util.NumeroLetra;
import br.edu.ifsul.gui.util.SoNumeros;
import br.edu.ifsul.cs.Controle;
import br.edu.ifsul.lpoo.cs.model.Arma;
import br.edu.ifsul.lpoo.cs.model.Municao;
import br.edu.ifsul.lpoo.cs.model.TipoArma;
import br.edu.ifsul.lpoo.cs.model.dao.ModeloPersistencia;
import br.edu.ifsul.lpoo.cs.model.dao.PersistenciaJDBC;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class JPanelEdicao extends javax.swing.JPanel {

    private JPanelJogador pnlJogador;
    private ModeloPersistencia persistencia;
    Boolean editar = false;
    
    //construtor
    public JPanelEdicao(JPanelJogador pnlJogador) {
        this.pnlJogador = pnlJogador;
        initComponents();
        txtId.setDocument(new SoNumeros());
        txtValor.setDocument(new SoNumeros());//validação para só numeros
        txtCalibre.setDocument(new SoNumeros());//validação para só numeros 
        txtDescricao.setDocument(new NumeroLetra());//validação para só numeros e letra
    }
    public void populaFormulario(Arma a){

        if(a == null){//se o parametro estiver nullo, limpa o formulário para deixar pronto para a nova insercao de registro
            txtId.setText("");
            txtDescricao.setText("");
            txtValor.setText("");
            txtCalibre.setText("");
            cbxTipoArma.setSelectedIndex(0);
            cbxMunicao.setSelectedIndex(0);
            editar = false;
            txtId.setEditable(true);
        }else{//carrega os campos com os dados da arma que foram trazidos da seleção da listagem
            txtId.setText(a.getId().toString());
            txtDescricao.setText(a.getDescricao());
            txtValor.setText(a.getValor().toString());
            txtCalibre.setText(a.getCalibre().toString());
            cbxTipoArma.setSelectedItem(a.getTipoarma());
            cbxMunicao.setSelectedItem(a.getMunicao());
            editar = true;
            txtId.setEditable(false);
        }
    }
    /*funcao preenche o combobox da tipo arma*/
    public void populaComboTipoArma(){
        try {
            List<TipoArma> listp = pnlJogador.getControle().getConexaoJDBC().listTipoArma();
            cbxTipoArma.removeAllItems();
            if(listp != null){
                for(TipoArma a: listp){
                    cbxTipoArma.addItem(a);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }/*funcao preenche o combobox da Municao*/
    public void populaComboMunicao(){
        try {
            List<Municao> lism = pnlJogador.getControle().getConexaoJDBC().listMunicao();
            cbxMunicao.removeAllItems();
            if(lism != null){
                for(Municao m: lism){
                    cbxMunicao.addItem(m);
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Arma getArma(){
        
        Arma a = null;
        
        int id = Integer.parseInt(txtId.getText().trim());
        Float calibre = Float.parseFloat(txtCalibre.getText().trim().replace(',','.'));
        String descricao = txtDescricao.getText().trim();
        Float valor = Float.parseFloat(txtValor.getText().trim().replace(',','.'));
        Integer intMunicao = cbxMunicao.getSelectedIndex();
        Integer intTipoArma = cbxTipoArma.getSelectedIndex();
        
        if(id != 0 && descricao != null && intMunicao != null && intTipoArma != null && intMunicao != null && valor != null){
            if(descricao.length() >= 3) {
                a = new Arma();
                a.setId(id);
                a.setCalibre(calibre);
                a.setDescricao(descricao);
                a.setValor(valor);
                a.setMunicao((Municao) cbxMunicao.getSelectedItem());
                a.setTipoarma((TipoArma) cbxTipoArma.getSelectedItem());

            } else {
                JOptionPane.showMessageDialog(this, "Descricao deve ter pelo menos 3 caracteres!!!", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }       
       return a;        
    }
    /*Inserção da arma pela persistenciaJDBC*/
    public boolean insereArma(Arma a) throws Exception {
        try {
            persistencia = pnlJogador.getControle().getConexaoJDBC();
            if(persistencia.conexaoAberta()) {
                System.out.println("Abriu conexao via JDBC");
                persistencia.persist(a);
                System.out.println("Fechou conexao via JDBC");
                return true;
            }else{
                System.out.println("Nao conseguiu abrir conexao via jdbc");
            }
            return false;
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, e, "catch", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    /*funcao que procura arma usando a persistenciaJDBC*/
    public boolean procuraArma(Arma a) throws Exception {
        try {
            persistencia = pnlJogador.getControle().getConexaoJDBC();
            if(persistencia.conexaoAberta()) {
                System.out.println("Abriu conexao via JDBC");
                if(persistencia.find(a.getClass(), a.getId()) != null){
                    return true;
                }
                return false;
            }else{
                System.out.println("Nao conseguiu abrir conexao via jdbc");
                return false;
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(this, e, "catch", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        pnlSul = new javax.swing.JPanel();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlCentro = new javax.swing.JPanel();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblTipoArma = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        lblCalibre = new javax.swing.JLabel();
        cbxTipoArma = new javax.swing.JComboBox<>();
        txtCalibre = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        cbxMunicao = new javax.swing.JComboBox<>();
        txtDescricao = new javax.swing.JTextField();

        jLabel1.setText("jLabel1");

        setLayout(new java.awt.BorderLayout());

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        pnlSul.add(btnSalvar);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlSul.add(btnCancelar);

        add(pnlSul, java.awt.BorderLayout.PAGE_END);

        pnlCentro.setLayout(new java.awt.GridBagLayout());

        lblId.setText("ID:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblId, gridBagConstraints);

        txtId.setColumns(20);
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        pnlCentro.add(txtId, gridBagConstraints);

        lblTipoArma.setText("TipoArma:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblTipoArma, gridBagConstraints);

        lblDescricao.setText("Descrição");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblDescricao, gridBagConstraints);

        lblValor.setText("Valor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblValor, gridBagConstraints);

        txtValor.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        pnlCentro.add(txtValor, gridBagConstraints);

        lblCalibre.setText("Calibre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        pnlCentro.add(lblCalibre, gridBagConstraints);

        cbxTipoArma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoArmaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.ipadx = 168;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        pnlCentro.add(cbxTipoArma, gridBagConstraints);

        txtCalibre.setColumns(20);
        txtCalibre.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtCalibre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCalibreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
        pnlCentro.add(txtCalibre, gridBagConstraints);

        jLabel2.setText("Munição:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        pnlCentro.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        pnlCentro.add(cbxMunicao, gridBagConstraints);

        txtDescricao.setColumns(20);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        pnlCentro.add(txtDescricao, gridBagConstraints);

        add(pnlCentro, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        Arma arPersistir = getArma();
            if(arPersistir != null){
                
                //Passo 2:  verificacao da acao: incluir novo ou alterar existente 
                if(!editar){
                     //Passo 3:  persistir
                    //insert
                    boolean insert;
                    try {
                        if (procuraArma(arPersistir)){//usa o find da persistenciaJDBC para verificar se a arma a ser incluido ja nao existe no banco
                            JOptionPane.showMessageDialog(this, "O ID JÁ É USADO", "Inserção", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            try {
                                insert = insereArma(arPersistir);
                                if (insert) {
                                    JOptionPane.showMessageDialog(this, "Arma inserido com sucesso", "Inserção", JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(this, "ERRO AO INSERIR ARMA", "Inserção", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } catch (Exception ex) {
                                Logger.getLogger(JPanelEdicao.class.getName()).log(Level.SEVERE, null, ex);
                                JOptionPane.showMessageDialog(this, "ERRO AO INSERIR ARMA", "Inserção", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    } catch(Exception ex){
                        Logger.getLogger(JPanelEdicao.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, "ERRO AO INSERIR ARMA", "Inserção", JOptionPane.INFORMATION_MESSAGE);
                    }
                }else{
                    //update
                    boolean update;
                    try {
                        update = insereArma(arPersistir);//persiste o update da arma
                        if (update) {
                            JOptionPane.showMessageDialog(this, "Arma editado com sucesso", "Edição", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "ERRO AO EDITAR ARMA", "Edição", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(JPanelEdicao.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(this, "ERRO AO EDITAR ARMA: "+ ex, "Edição", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                pnlJogador.showTela("tela_listagem");
            }else{
                JOptionPane.showMessageDialog(this, "Preencha o formulário!!!", "Validação", JOptionPane.INFORMATION_MESSAGE);
            }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        pnlJogador.showTela("tela_listagem");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cbxTipoArmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoArmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoArmaActionPerformed

    private void txtCalibreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCalibreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCalibreActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<Municao> cbxMunicao;
    private javax.swing.JComboBox<TipoArma> cbxTipoArma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblCalibre;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblTipoArma;
    private javax.swing.JLabel lblValor;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlSul;
    private javax.swing.JFormattedTextField txtCalibre;
    private javax.swing.JTextField txtDescricao;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
