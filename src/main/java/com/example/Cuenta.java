package com.example;

public abstract class Cuenta {
    private String titular;
    protected double saldo;

    public Cuenta(String titular, double saldoInicial) {
        this.titular = titular;
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }
        this.saldo = saldoInicial;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void consignar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El valor a consignar debe ser mayor que cero");
        }
        this.saldo += valor;
    }

    public void retirar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El valor a retirar debe ser mayor que cero");
        }
        this.saldo -= valor;
    }
}
