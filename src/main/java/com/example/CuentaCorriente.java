package com.example;

public class CuentaCorriente extends Cuenta {
    
    public static final double COMISION_RETIRO = 10000;

    public CuentaCorriente(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    public void retirar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("El valor a retirar debe ser mayor que cero");
        }
        this.saldo -= (valor + COMISION_RETIRO);
    }
}
