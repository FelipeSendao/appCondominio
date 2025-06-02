/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Administrador
 */
public class Mensalidade {
    private int id;
    private int residenciaId;
    private int mes;
    private int ano;
    private boolean pago;

    public Mensalidade(int residenciaId, int mes, int ano, boolean pago) {
        this.residenciaId = residenciaId;
        this.mes = mes;
        this.ano = ano;
        this.pago = pago;
    }

    public Mensalidade() {
    }

    public int getResidenciaId() {
        return residenciaId; 
    }
    
    public int getMes() { 
        return mes; 
    }
    
    public int getAno() { 
        return ano; 
    }
    
    public boolean isPago() { 
        return pago; 
    }

    public void setPago(boolean pago) { 
        this.pago = pago; 
    }

    @Override
    public String toString() {
        return mes + "/" + ano + " - " + (pago ? "Pago" : "Atrasado");
    }
}
