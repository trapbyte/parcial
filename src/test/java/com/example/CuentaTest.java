package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CuentaTest {

    @Test
    public void testConsignarValorValido() {
        CuentaAhorro cuenta = new CuentaAhorro("Sergio", 100000);
        cuenta.consignar(50000);
        assertEquals(150000, cuenta.getSaldo());
    }

    @Test
    public void testConsignarValorInvalido() {
        CuentaAhorro cuenta = new CuentaAhorro("Sergio", 100000);
        assertThrows(IllegalArgumentException.class, () -> {
            cuenta.consignar(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            cuenta.consignar(-1000);
        });
    }

    @Test
    public void testRetirarCuentaAhorroSaldoSuficiente() {
        CuentaAhorro cuenta = new CuentaAhorro("Sergio", 100000);
        cuenta.retirar(40000);
        assertEquals(60000, cuenta.getSaldo());
    }

    @Test
    public void testRetirarCuentaAhorroSaldoInsuficiente() {
        CuentaAhorro cuenta = new CuentaAhorro("Sergio", 100000);
        assertThrows(IllegalArgumentException.class, () -> {
            cuenta.retirar(150000);
        });
    }

    @Test
    public void testRetirarCuentaCorrienteComision() {
        CuentaCorriente cuenta = new CuentaCorriente("Sergio", 280000);
        cuenta.retirar(50000);
        // Debe descontar los 50,000 + 10,000 de comisión = 60,000. Saldo = 220,000
        assertEquals(220000, cuenta.getSaldo());
    }
}
