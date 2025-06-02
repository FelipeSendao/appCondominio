/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Administrador
 */
public class Morador {
    private String nome;
    private int idade;
    private String rg;
    private String cpf;

    public Morador(String nome, int idade, String rg, String cpf) {
        this.nome = nome;
        this.idade = idade;
        this.rg = rg;
        this.cpf = cpf;
    }

    public Morador() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return nome + "-" + cpf;
    }
    
}
