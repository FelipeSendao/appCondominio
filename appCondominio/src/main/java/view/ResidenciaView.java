/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.MoradorController;
import controller.ResidenciaController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Morador;
import model.Residencia;

/**
 *
 * @author Administrador
 */
public class ResidenciaView extends javax.swing.JFrame {
    
    private JTextField ruaField = new JTextField(15);
    private JTextField numeroField = new JTextField(5);
    private JTextField cepField = new JTextField(10);
    private JTextField cpfProprietarioField = new JTextField(15);

    private JTextField cpfMoradorField = new JTextField(15);
    private JTextField residenciaIdField = new JTextField(5);

    private JTextField idBuscaField = new JTextField(5);

    private JTable tabelaMoradores;
    private DefaultTableModel tabelaModel;

    private final ResidenciaController residenciaController = new ResidenciaController();
    private final MoradorController moradorController = new MoradorController();

    /**
     * Creates new form ResidenciaView
     */
    public ResidenciaView() {
        super("Gerenciamento de Residências");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel cadastroPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        cadastroPanel.setBorder(BorderFactory.createTitledBorder("Cadastrar Residência"));

        cadastroPanel.add(new JLabel("Rua:"));
        cadastroPanel.add(ruaField);

        cadastroPanel.add(new JLabel("Número:"));
        cadastroPanel.add(numeroField);

        cadastroPanel.add(new JLabel("CEP:"));
        cadastroPanel.add(cepField);

        cadastroPanel.add(new JLabel("CPF do Proprietário:"));
        cadastroPanel.add(cpfProprietarioField);

        JButton cadastrarBtn = new JButton("Cadastrar");
        JButton listarBtn = new JButton("Listar Todas");

        cadastrarBtn.addActionListener(e -> cadastrarResidencia());
        listarBtn.addActionListener(e -> listarResidencias());

        cadastroPanel.add(cadastrarBtn);
        cadastroPanel.add(listarBtn);

        JPanel vinculoPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        vinculoPanel.setBorder(BorderFactory.createTitledBorder("Vincular Morador à Residência"));

        vinculoPanel.add(new JLabel("CPF do Morador:"));
        vinculoPanel.add(cpfMoradorField);

        vinculoPanel.add(new JLabel("ID da Residência:"));
        vinculoPanel.add(residenciaIdField);

        JButton vincularBtn = new JButton("Vincular");
        vincularBtn.addActionListener(e -> vincularMorador());

        vinculoPanel.add(vincularBtn);
        vinculoPanel.add(new JLabel(""));

        JPanel listaPanel = new JPanel(new FlowLayout());
        listaPanel.setBorder(BorderFactory.createTitledBorder("Listar Moradores de uma Residência"));

        listaPanel.add(new JLabel("ID da Residência:"));
        listaPanel.add(idBuscaField);
        JButton listarMoradoresBtn = new JButton("Listar Moradores");
        listarMoradoresBtn.addActionListener(e -> listarMoradoresDaResidencia());
        listaPanel.add(listarMoradoresBtn);

        String[] colunas = {"Nome", "Idade", "RG", "CPF"};
        tabelaModel = new DefaultTableModel(colunas, 0);
        tabelaMoradores = new JTable(tabelaModel);
        JScrollPane scrollPane = new JScrollPane(tabelaMoradores);
        scrollPane.setPreferredSize(new Dimension(560, 150));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(cadastroPanel, BorderLayout.NORTH);
        topPanel.add(vinculoPanel, BorderLayout.CENTER);
        topPanel.add(listaPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
        initComponents();
    }
    
    private void cadastrarResidencia() {
        String rua = ruaField.getText().trim();
        String numero = numeroField.getText().trim();
        String cep = cepField.getText().trim();
        String cpfProprietario = cpfProprietarioField.getText().trim();

        if (rua.isEmpty() || numero.isEmpty() || cep.isEmpty() || cpfProprietario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.");
            return;
        }

        boolean sucesso = residenciaController.cadastrarResidencia(rua, numero, cep, cpfProprietario);
        if (sucesso) {
            JOptionPane.showMessageDialog(this, "Residência cadastrada com sucesso!");
            ruaField.setText("");
            numeroField.setText("");
            cepField.setText("");
            cpfProprietarioField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Erro: Proprietário não encontrado.");
        }
    }

    private void listarResidencias() {
        List<Residencia> lista = residenciaController.listarResidencias();
        StringBuilder sb = new StringBuilder("Residências Cadastradas:\n\n");
        for (Residencia r : lista) {
            sb.append(r.toString()).append("\n");
        }

        JTextArea area = new JTextArea(sb.toString());
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(500, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Lista de Residências", JOptionPane.INFORMATION_MESSAGE);
    }

    private void vincularMorador() {
        String cpf = cpfMoradorField.getText().trim();
        String idStr = residenciaIdField.getText().trim();

        if (cpf.isEmpty() || idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha CPF e ID.");
            return;
        }

        try {
            int residenciaId = Integer.parseInt(idStr);
            Morador morador = moradorController.buscarPorCpf(cpf);
            if (morador == null) {
                JOptionPane.showMessageDialog(this, "Morador não encontrado.");
                return;
            }

            moradorController.vincularMoradorAResidencia(cpf, residenciaId);
            JOptionPane.showMessageDialog(this, "Morador vinculado com sucesso.");
            cpfMoradorField.setText("");
            residenciaIdField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void listarMoradoresDaResidencia() {
        String idStr = idBuscaField.getText().trim();
        try {
            int residenciaId = Integer.parseInt(idStr);
            List<Morador> moradores = moradorController.listarPorResidencia(residenciaId);

            tabelaModel.setRowCount(0); // Limpa a tabela
            for (Morador m : moradores) {
                tabelaModel.addRow(new Object[]{
                        m.getNome(),
                        m.getIdade(),
                        m.getRg(),
                        m.getCpf()
                });
            }

            if (moradores.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhum morador encontrado para essa residência.");
            }
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
            java.util.logging.Logger.getLogger(ResidenciaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ResidenciaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ResidenciaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResidenciaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ResidenciaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
