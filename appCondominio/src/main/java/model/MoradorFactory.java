/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Administrador
 */
public class MoradorFactory {
    public static Morador criarMorador(String nome, int idade, String rg, String cpf) {
        return new Morador(nome, idade, rg, cpf);
    }
}
