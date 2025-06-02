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
public class MensalidadeDAO {
    
    public MensalidadeDAO() {
        try (Statement stmt = DBConnection.getInstance().createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS mensalidade (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "residencia_id INTEGER, " +
                    "mes INTEGER, ano INTEGER, pago BOOLEAN)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean existeMensalidade(int residenciaId, int mes, int ano) {
        String sql = "SELECT COUNT(*) FROM mensalidade WHERE residencia_id = ? AND mes = ? AND ano = ?";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setInt(1, residenciaId);
            pstmt.setInt(2, mes);
            pstmt.setInt(3, ano);
            ResultSet rs = pstmt.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void salvar(Mensalidade m) {
        
        if (existeMensalidade(m.getResidenciaId(), m.getMes(), m.getAno())) {
            System.out.println("Mensalidade já registrada para esse mês/ano.");
            return;
        }

        String sql = "INSERT INTO mensalidade (residencia_id, mes, ano, pago) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setInt(1, m.getResidenciaId());
            pstmt.setInt(2, m.getMes());
            pstmt.setInt(3, m.getAno());
            pstmt.setBoolean(4, m.isPago());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mensalidade> listarPorResidencia(int residenciaId) {
        List<Mensalidade> lista = new ArrayList<>();
        String sql = "SELECT * FROM mensalidade WHERE residencia_id = ?";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setInt(1, residenciaId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Mensalidade m = new Mensalidade(
                        rs.getInt("residencia_id"),
                        rs.getInt("mes"),
                        rs.getInt("ano"),
                        rs.getBoolean("pago")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void atualizarStatus(int residenciaId, int mes, int ano, boolean pago) {
        String sql = "UPDATE mensalidade SET pago = ? WHERE residencia_id = ? AND mes = ? AND ano = ?";
        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setBoolean(1, pago);
            pstmt.setInt(2, residenciaId);
            pstmt.setInt(3, mes);
            pstmt.setInt(4, ano);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public String gerarResumoFinanceiro(int residenciaId) {
        String sql = "SELECT " +
                     "SUM(CASE WHEN pago = 1 THEN 1 ELSE 0 END) AS totalPagas, " +
                     "SUM(CASE WHEN pago = 0 THEN 1 ELSE 0 END) AS totalAtrasadas " +
                     "FROM mensalidade WHERE residencia_id = ?";

        try (PreparedStatement pstmt = DBConnection.getInstance().prepareStatement(sql)) {
            pstmt.setInt(1, residenciaId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int pagas = rs.getInt("totalPagas");
                int atrasadas = rs.getInt("totalAtrasadas");

                return "Mensalidades da Residência " + residenciaId + ":\n" +
                       "Pagas: " + pagas + "\n" +
                       "Atrasadas: " + atrasadas;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "Erro ao gerar resumo financeiro.";
    }
}
