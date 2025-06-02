/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import view.EdicaoView;
import view.MainView;
import view.MensalidadeView;
import view.ResidenciaEdicaoView;
import view.ResidenciaView;

/**
 *
 * @author Administrador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            
            JFrame frame = new JFrame("Sistema de Gestão de Condomínio");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 350);
            frame.setLocationRelativeTo(null);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(6, 1, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JButton btnMoradores = new JButton("Gerenciar Moradores");
            btnMoradores.addActionListener(e -> new MainView());

            JButton btnResidencias = new JButton("Gerenciar Residências");
            btnResidencias.addActionListener(e -> new ResidenciaView());

            JButton btnMensalidades = new JButton("Mensalidades");
            btnMensalidades.addActionListener(e -> new MensalidadeView());

            JButton btnEditarMorador = new JButton("Editar / Remover Morador");
            btnEditarMorador.addActionListener(e -> new EdicaoView());

            JButton btnEditarResidencia = new JButton("Editar / Remover Residência");
            btnEditarResidencia.addActionListener(e -> new ResidenciaEdicaoView());

            JButton btnSair = new JButton("Sair");
            btnSair.addActionListener(e -> System.exit(0));

            panel.add(btnMoradores);
            panel.add(btnResidencias);
            panel.add(btnMensalidades);
            panel.add(btnEditarMorador);
            panel.add(btnEditarResidencia);
            panel.add(btnSair);

            frame.add(panel);
            frame.setVisible(true);
        });
    }
    
}
