/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.ResidenciaController;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Morador;
import model.MoradorDAO;
import model.Residencia;

/**
 *
 * @author Administrador
 */
public class ResidenciaEdicaoView extends javax.swing.JFrame {
    
    private JTextField idField = new JTextField(5);
    private JTextField ruaField = new JTextField(15);
    private JTextField numeroField = new JTextField(5);
    private JTextField cepField = new JTextField(10);
    private JTextField cpfProprietarioField = new JTextField(15);
    private JCheckBox emDiaCheckBox = new JCheckBox("Em dia");

    private JButton buscarBtn = new JButton("Buscar");
    private JButton salvarBtn = new JButton("Salvar Alterações");
    private JButton removerBtn = new JButton("Remover Residência");

    private ResidenciaController controller = new ResidenciaController();
    private MoradorDAO moradorDAO = new MoradorDAO();

    /**
     * Creates new form ResidenciaEdicaoView
     */
    public ResidenciaEdicaoView() {
        super("Edição de Residência");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(8, 2, 10, 10));

        add(new JLabel("ID da Residência:"));
        add(idField);
        add(new JLabel(""));
        add(buscarBtn);

        add(new JLabel("Rua:"));
        add(ruaField);

        add(new JLabel("Número:"));
        add(numeroField);

        add(new JLabel("CEP:"));
        add(cepField);

        add(new JLabel("CPF Proprietário:"));
        add(cpfProprietarioField);

        add(new JLabel("Situação:"));
        add(emDiaCheckBox);

        add(salvarBtn);
        add(removerBtn);

        setCamposEditaveis(false);

        buscarBtn.addActionListener(e -> buscarResidencia());
        salvarBtn.addActionListener(e -> salvarAlteracoes());
        removerBtn.addActionListener(e -> removerResidencia());

        setVisible(true);
        initComponents();
    }
    
    private void setCamposEditaveis(boolean ativo) {
        ruaField.setEnabled(ativo);
        numeroField.setEnabled(ativo);
        cepField.setEnabled(ativo);
        cpfProprietarioField.setEnabled(ativo);
        emDiaCheckBox.setEnabled(ativo);
        salvarBtn.setEnabled(ativo);
        removerBtn.setEnabled(ativo);
    }

    private void buscarResidencia() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            Residencia r = controller.buscarPorId(id);

            if (r == null) {
                JOptionPane.showMessageDialog(this, "Residência não encontrada.");
                setCamposEditaveis(false);
                return;
            }

            ruaField.setText(r.getRua());
            numeroField.setText(r.getNumero());
            cepField.setText(r.getCep());
            cpfProprietarioField.setText(r.getProprietario().getCpf());
            emDiaCheckBox.setSelected(r.isEmDia());

            setCamposEditaveis(true);

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void salvarAlteracoes() {
        try {
            int id = Integer.parseInt(idField.getText().trim());
            String rua = ruaField.getText().trim();
            String numero = numeroField.getText().trim();
            String cep = cepField.getText().trim();
            String cpfProprietario = cpfProprietarioField.getText().trim();
            boolean emDia = emDiaCheckBox.isSelected();

            if (rua.isEmpty() || numero.isEmpty() || cep.isEmpty() || cpfProprietario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
                return;
            }

            Morador proprietario = moradorDAO.buscarPorCpf(cpfProprietario);
            if (proprietario == null) {
                JOptionPane.showMessageDialog(this, "Proprietário não encontrado.");
                return;
            }

            boolean atualizado = controller.atualizarResidencia(id, rua, numero, cep, cpfProprietario, emDia);
            if (atualizado) {
                JOptionPane.showMessageDialog(this, "Residência atualizada com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar residência.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void removerResidencia() {
        try {
            int id = Integer.parseInt(idField.getText().trim());

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Deseja remover esta residência e TODOS os moradores vinculados?",
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                controller.removerResidencia(id);
                JOptionPane.showMessageDialog(this, "Residência removida com sucesso.");
                limparCampos();
                setCamposEditaveis(false);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void limparCampos() {
        ruaField.setText("");
        numeroField.setText("");
        cepField.setText("");
        cpfProprietarioField.setText("");
        emDiaCheckBox.setSelected(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ResidenciaEdicaoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResidenciaEdicaoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResidenciaEdicaoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResidenciaEdicaoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResidenciaEdicaoView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
