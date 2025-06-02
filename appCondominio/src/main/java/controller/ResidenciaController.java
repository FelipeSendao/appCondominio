/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Morador;
import model.MoradorDAO;
import model.Residencia;
import model.ResidenciaDAO;

/**
 *
 * @author Administrador
 */
public class ResidenciaController {
    
    private ResidenciaDAO residenciaDAO = new ResidenciaDAO();
    private MoradorDAO moradorDAO = new MoradorDAO();

    public boolean cadastrarResidencia(String rua, String numero, String cep, String cpfProprietario) {
        Morador proprietario = moradorDAO.buscarPorCpf(cpfProprietario);
        if (proprietario == null) return false;

        Residencia r = new Residencia(rua, numero, cep, proprietario);
        residenciaDAO.salvar(r);
        return true;
    }

    public List<Residencia> listarResidencias() {
        return residenciaDAO.listar();
    }

    public Residencia buscarPorId(int id) {
        return residenciaDAO.buscarPorId(id);
    }

    public boolean atualizarResidencia(int id, String rua, String numero, String cep, String cpfProprietario, boolean emDia) {
        Morador proprietario = moradorDAO.buscarPorCpf(cpfProprietario);
        if (proprietario == null) return false;

        Residencia r = new Residencia(rua, numero, cep, proprietario);
        r.setId(id);
        r.setEmDia(emDia);

        residenciaDAO.atualizar(r);
        return true;
    }

    public void removerResidencia(int id) {
        residenciaDAO.removerResidencia(id);
    }
}
