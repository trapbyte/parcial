package com.example;

public class CuentaAhorro extends Cuenta {

    public CuentaAhorro(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    public void retirar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El valor a retirar debe ser mayor que cero");
        }
        if (saldo - valor < 0) {
            throw new IllegalArgumentException("La cuenta de ahorros no puede quedar con saldo negativo");
        }
        this.saldo -= valor;
    }
}
