package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FacturaTest {
    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Sergio", "123456", "sergio@test.com");
    }

    @Test
    public void testCrearFacturaSubtotalNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Factura("F001", cliente, "Computador", -1000, 19);
        });
    }

    @Test
    public void testCalcularImpuesto() {
        Factura factura = new Factura("F001", cliente, "Computador", 100000, 19);
        double impuesto = factura.calcularImpuesto();
        assertEquals(19000, impuesto, 0.01);
    }

    @Test
    public void testCalcularTotal() {
        Factura factura = new Factura("F001", cliente, "Computador", 100000, 19);
        double total = factura.calcularTotal();
        assertEquals(119000, total, 0.01);
    }

    @Test
    public void testPagarFacturaConMontoCorrecto() {
        Factura factura = new Factura("F002", cliente, "Mouse", 50000, 0);
        ServicioFacturacion servicio = new ServicioFacturacion();
        servicio.crearFactura(factura);
        Cuenta cuenta = new CuentaAhorro("Sergio", 100000);

        MedioPago pagoPSE = new PagoPSE();
        boolean resultado = servicio.pagarFactura("F002", pagoPSE, 50000, cuenta);
        
        assertTrue(resultado);
        assertEquals(EstadoFactura.PAGADA, factura.getEstado());
    }

    @Test
    public void testPagarFacturaConMontoMenorAlTotal() {
        Factura factura = new Factura("F003", cliente, "Teclado", 100000, 19); // total 119000
        ServicioFacturacion servicio = new ServicioFacturacion();
        servicio.crearFactura(factura);
        Cuenta cuenta = new CuentaAhorro("Sergio", 150000);

        MedioPago pagoPSE = new PagoPSE();
        boolean resultado = servicio.pagarFactura("F003", pagoPSE, 100000, cuenta); // 100000 < 119000
        
        assertFalse(resultado);
        assertEquals(EstadoFactura.PENDIENTE, factura.getEstado());
    }

    @Test
    public void testPagarFacturaYaPagada() {
        Factura factura = new Factura("F004", cliente, "Monitor", 200000, 0);
        ServicioFacturacion servicio = new ServicioFacturacion();
        servicio.crearFactura(factura);
        Cuenta cuenta = new CuentaAhorro("Sergio", 500000);

        MedioPago pagoPSE = new PagoPSE();
        servicio.pagarFactura("F004", pagoPSE, 200000, cuenta); // Primer pago
        
        boolean resultadoSegundoPago = servicio.pagarFactura("F004", pagoPSE, 200000, cuenta); // Segundo pago
        assertFalse(resultadoSegundoPago);
    }
}
