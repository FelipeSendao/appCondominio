/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Mensalidade;
import model.MensalidadeDAO;

/**
 *
 * @author Administrador
 */
public class MensalidadeController {
    private MensalidadeDAO dao = new MensalidadeDAO();

    public void registrarMensalidade(int residenciaId, int mes, int ano, boolean pago) {
        Mensalidade m = new Mensalidade(residenciaId, mes, ano, pago);
        dao.salvar(m);
    }

    public List<Mensalidade> listarPorResidencia(int residenciaId) {
        return dao.listarPorResidencia(residenciaId);
    }

    public void atualizarStatus(int residenciaId, int mes, int ano, boolean pago) {
        dao.atualizarStatus(residenciaId, mes, ano, pago);
    }
    
    public String gerarResumoFinanceiro(int residenciaId) {
        return dao.gerarResumoFinanceiro(residenciaId);
    }
    
}
