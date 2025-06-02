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
public class ResidenciaDAO {
    
    public ResidenciaDAO() {
        try (Statement stmt = DBConnection.getInstance().createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS residencia (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "rua TEXT, numero TEXT, cep TEXT, " +
                    "proprietario_cpf TEXT, em_dia BOOLEAN)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void salvar(Residencia r) {
        String sql = "INSERT INTO residencia (rua, numero, cep, proprietario_cpf, em_dia) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setString(1, r.getRua());
            pstmt.setString(2, r.getNumero());
            pstmt.setString(3, r.getCep());
            pstmt.setString(4, r.getProprietario().getCpf());
            pstmt.setBoolean(5, r.isEmDia());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Residencia> listar() {
        List<Residencia> lista = new ArrayList<>();
        String sql = "SELECT * FROM residencia";

        MoradorDAO moradorDAO = new MoradorDAO();

        try (Statement stmt = DBConnection.getInstance().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");
                String cep = rs.getString("cep");
                boolean emDia = rs.getBoolean("em_dia");
                String cpfProprietario = rs.getString("proprietario_cpf");

                Morador proprietario = moradorDAO.buscarPorCpf(cpfProprietario);
                if (proprietario == null) continue;

                Residencia r = new Residencia(rua, numero, cep, proprietario);
                r.setId(id);
                r.setEmDia(emDia);

                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Residencia buscarPorId(int idResidencia) {
        String sql = "SELECT * FROM residencia WHERE id = ?";
        MoradorDAO moradorDAO = new MoradorDAO();

        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setInt(1, idResidencia);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String rua = rs.getString("rua");
                String numero = rs.getString("numero");
                String cep = rs.getString("cep");
                boolean emDia = rs.getBoolean("em_dia");
                String cpfProprietario = rs.getString("proprietario_cpf");

                Morador proprietario = moradorDAO.buscarPorCpf(cpfProprietario);
                if (proprietario == null) return null;

                Residencia r = new Residencia(rua, numero, cep, proprietario);
                r.setId(idResidencia);
                r.setEmDia(emDia);
                return r;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void atualizar(Residencia r) {
        String sql = "UPDATE residencia SET rua = ?, numero = ?, cep = ?, proprietario_cpf = ?, em_dia = ? WHERE id = ?";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setString(1, r.getRua());
            pstmt.setString(2, r.getNumero());
            pstmt.setString(3, r.getCep());
            pstmt.setString(4, r.getProprietario().getCpf());
            pstmt.setBoolean(5, r.isEmDia());
            pstmt.setInt(6, r.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerResidencia(int residenciaId) {
        // Remover moradores vinculados
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(
                "DELETE FROM morador WHERE residencia_id = ?")) {
            pstmt.setInt(1, residenciaId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Remover residência
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(
                "DELETE FROM residencia WHERE id = ?")) {
            pstmt.setInt(1, residenciaId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
