// @author Vinícius Trindade
package model.entities;

import model.exceptions.DomainException;

public class Conta {

    private Integer numero;
    private String cliente;
    private Double saldo;
    private Double limiteSaque;

    public Conta() {
    }

    public Conta(Integer numero, String cliente, Double saldo, Double limiteSaque) throws DomainException {
       
        this.numero = numero;
        this.cliente = cliente;
        this.saldo = saldo;
        this.limiteSaque = limiteSaque;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Double getLimiteSaque() {
        return limiteSaque;
    }

    public void saque(Double valor) throws DomainException {
        if (valor > limiteSaque) {
            throw new DomainException("O valor excede o limite de saque da conta!");
        } else if (valor > saldo) {
            throw new DomainException("Saldo insuficiente! ");
        } else {
            saldo -= valor;
        }
    }

    @Override
    public String toString() {
       return "Novo saldo: " + String.format("%.2f", getSaldo());
    }

}
