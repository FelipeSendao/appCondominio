/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import model.Morador;
import model.MoradorDAO;
import model.MoradorFactory;

/**
 *
 * @author Administrador
 */
public class MoradorController {
    
    private MoradorDAO dao = new MoradorDAO();

    public void adicionarMorador(String nome, int idade, String rg, String cpf) {
        Morador m = MoradorFactory.criarMorador(nome, idade, rg, cpf);
        dao.salvar(m); // método que salva com residencia_id NULL
    }

    public void adicionarMoradorComResidencia(String nome, int idade, String rg, String cpf, int residenciaId) {
        Morador m = MoradorFactory.criarMorador(nome, idade, rg, cpf);
        dao.salvar(m, residenciaId); // método com residencia_id
    }

    public List<Morador> listarOrdenadoPorNome() {
        List<Morador> lista = dao.listar();

        if (lista == null) return List.of();

        return lista.stream()
                .filter(m -> m.getNome() != null)
                .sorted(Comparator.comparing(Morador::getNome, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public Morador buscarPorCpf(String cpf) {
        return dao.buscarPorCpf(cpf);
    }

    public void atualizarMorador(Morador m) {
        dao.atualizar(m);
    }

    public void removerMorador(String cpf) {
        dao.removerPorCpf(cpf);
    }

    public List<Morador> listarPorResidencia(int residenciaId) {
        return dao.listarPorResidencia(residenciaId);
    }

    public void vincularMoradorAResidencia(String cpf, int residenciaId) {
        dao.vincularResidencia(cpf, residenciaId);
    }
}
