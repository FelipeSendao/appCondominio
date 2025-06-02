/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Residencia {
    private int id;
    private String rua;
    private String numero;
    private String cep;
    private Morador proprietario;
    private List<Morador> moradores;
    private boolean emDia;

    public Residencia(String rua, String numero, String cep, Morador proprietario) {
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.proprietario = proprietario;
        this.emDia = true;
        this.moradores = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Morador getProprietario() {
        return proprietario;
    }

    public void setProprietario(Morador proprietario) {
        this.proprietario = proprietario;
    }

    public boolean isEmDia() {
        return emDia;
    }

    public void setEmDia(boolean emDia) {
        this.emDia = emDia;
    }

    public List<Morador> getMoradores() {
        return moradores;
    }

    public void setMoradores(List<Morador> moradores) {
        this.moradores = moradores;
    }

    public void addMorador(Morador m) {
        if (!moradores.contains(m)) {
            moradores.add(m);
        }
    }

    public String getEnderecoCompleto() {
        return rua + ", " + numero + " - CEP: " + cep;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Endereço: " + getEnderecoCompleto() +
                " | Proprietário: " + (proprietario != null ? proprietario.getNome() : "N/A") +
                " | Situação: " + (emDia ? "Em dia" : "Em atraso");
    }
}
