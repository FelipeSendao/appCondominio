/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class MoradorDAO {
    
    public MoradorDAO() {
        try (Statement stmt = DBConnection.getInstance().createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS morador (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome TEXT NOT NULL, " +
                    "idade INTEGER, " +
                    "rg TEXT, " +
                    "cpf TEXT UNIQUE NOT NULL, " +
                    "residencia_id INTEGER)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Morador m) {
        String sql = "INSERT INTO morador (nome, idade, rg, cpf, residencia_id) VALUES (?, ?, ?, ?, NULL)";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setString(1, m.getNome());
            pstmt.setInt(2, m.getIdade());
            pstmt.setString(3, m.getRg());
            pstmt.setString(4, m.getCpf());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Morador m, int residenciaId) {
        String sql = "INSERT INTO morador (nome, idade, rg, cpf, residencia_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setString(1, m.getNome());
            pstmt.setInt(2, m.getIdade());
            pstmt.setString(3, m.getRg());
            pstmt.setString(4, m.getCpf());
            pstmt.setInt(5, residenciaId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Morador> listar() {
        List<Morador> moradores = new ArrayList<>();
        String sql = "SELECT * FROM morador";
        try (Statement stmt = DBConnection.getInstance().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                moradores.add(new Morador(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("rg"),
                        rs.getString("cpf")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moradores;
    }

    public List<Morador> listarPorResidencia(int residenciaId) {
        List<Morador> moradores = new ArrayList<>();
        String sql = "SELECT * FROM morador WHERE residencia_id = ?";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setInt(1, residenciaId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                moradores.add(new Morador(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("rg"),
                        rs.getString("cpf")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return moradores;
    }

    public Morador buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM morador WHERE cpf = ?";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Morador(
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("rg"),
                        rs.getString("cpf")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizar(Morador m) {
        String sql = "UPDATE morador SET nome = ?, idade = ?, rg = ? WHERE cpf = ?";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setString(1, m.getNome());
            pstmt.setInt(2, m.getIdade());
            pstmt.setString(3, m.getRg());
            pstmt.setString(4, m.getCpf());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerPorCpf(String cpf) {
        String sql = "DELETE FROM morador WHERE cpf = ?";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setString(1, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void vincularResidencia(String cpf, int residenciaId) {
        String sql = "UPDATE morador SET residencia_id = ? WHERE cpf = ?";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setInt(1, residenciaId);
            pstmt.setString(2, cpf);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
