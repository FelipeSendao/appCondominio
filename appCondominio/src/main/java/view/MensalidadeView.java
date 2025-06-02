/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.MensalidadeController;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.Mensalidade;

/**
 *
 * @author Administrador
 */
public class MensalidadeView extends javax.swing.JFrame {
    
    private JTextField residenciaIdField = new JTextField(5);
    private JTextField mesField = new JTextField(2);
    private JTextField anoField = new JTextField(4);
    private JCheckBox pagoCheckbox = new JCheckBox("Pago");

    private JButton registrarBtn = new JButton("Registrar Mensalidade");
    private JButton listarBtn = new JButton("Listar Mensalidades");
    private JButton atualizarBtn = new JButton("Atualizar Status");
    private JButton resumoBtn = new JButton("Resumo Financeiro");

    private JTextArea outputArea = new JTextArea(12, 40);
    private MensalidadeController controller = new MensalidadeController();

    /**
     * Creates new form MensalidadeView
     */
    public MensalidadeView() {
        super("Controle de Mensalidades");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(550, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Mensalidade"));

        formPanel.add(new JLabel("ID da Residência:"));
        formPanel.add(residenciaIdField);

        formPanel.add(new JLabel("Mês (1-12):"));
        formPanel.add(mesField);

        formPanel.add(new JLabel("Ano:"));
        formPanel.add(anoField);

        formPanel.add(new JLabel("Status:"));
        formPanel.add(pagoCheckbox);

        formPanel.add(registrarBtn);
        formPanel.add(atualizarBtn);

        // Botões extras
        JPanel botoesExtras = new JPanel(new FlowLayout());
        botoesExtras.add(listarBtn);
        botoesExtras.add(resumoBtn);

        // Área de saída
        outputArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(outputArea);

        // Eventos
        registrarBtn.addActionListener(e -> registrarMensalidade());
        atualizarBtn.addActionListener(e -> atualizarStatus());
        listarBtn.addActionListener(e -> listarMensalidades());
        resumoBtn.addActionListener(e -> mostrarResumo());

        add(formPanel, BorderLayout.NORTH);
        add(botoesExtras, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        setVisible(true);
        initComponents();
    }
    
    private void registrarMensalidade() {
        try {
            int residenciaId = Integer.parseInt(residenciaIdField.getText().trim());
            int mes = Integer.parseInt(mesField.getText().trim());
            int ano = Integer.parseInt(anoField.getText().trim());
            boolean pago = pagoCheckbox.isSelected();

            controller.registrarMensalidade(residenciaId, mes, ano, pago);
            JOptionPane.showMessageDialog(this, "Mensalidade registrada (ou já existente).");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos corretamente.");
        }
    }

    private void atualizarStatus() {
        try {
            int residenciaId = Integer.parseInt(residenciaIdField.getText().trim());
            int mes = Integer.parseInt(mesField.getText().trim());
            int ano = Integer.parseInt(anoField.getText().trim());
            boolean pago = pagoCheckbox.isSelected();

            controller.atualizarStatus(residenciaId, mes, ano, pago);
            JOptionPane.showMessageDialog(this, "Status atualizado.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Dados inválidos.");
        }
    }

    private void listarMensalidades() {
        try {
            int residenciaId = Integer.parseInt(residenciaIdField.getText().trim());
            List<Mensalidade> lista = controller.listarPorResidencia(residenciaId);

            outputArea.setText("Mensalidades da Residência " + residenciaId + ":\n");
            for (Mensalidade m : lista) {
                outputArea.append(m.toString() + "\n");
            }

            if (lista.isEmpty()) {
                outputArea.append("Nenhuma mensalidade encontrada.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Informe um ID de residência válido.");
        }
    }

    private void mostrarResumo() {
        try {
            int residenciaId = Integer.parseInt(residenciaIdField.getText().trim());
            String resumo = controller.gerarResumoFinanceiro(residenciaId);
            JOptionPane.showMessageDialog(this, resumo);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
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
            java.util.logging.Logger.getLogger(MensalidadeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MensalidadeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MensalidadeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MensalidadeView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MensalidadeView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
