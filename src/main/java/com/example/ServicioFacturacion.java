package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServicioFacturacion {
    private List<Cliente> clientes;
    private List<Factura> facturas;
    private List<Cuenta> cuentas;

    public ServicioFacturacion() {
        this.clientes = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.cuentas = new ArrayList<>();
    }

    public void registrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void registrarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public void crearFactura(Factura factura) {
        facturas.add(factura);
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Optional<Factura> buscarFacturaPorNumero(String numero) {
        return facturas.stream()
                .filter(f -> f.getNumero().equals(numero))
                .findFirst();
    }

    public boolean pagarFactura(String numeroFactura, MedioPago medioPago, double valor) {
        Optional<Factura> optFactura = buscarFacturaPorNumero(numeroFactura);
        if (optFactura.isEmpty()) {
            System.out.println("Factura no encontrada.");
            return false;
        }

        Factura factura = optFactura.get();

        if (factura.getEstado() == EstadoFactura.PAGADA) {
            System.out.println("Error: La factura ya se encuentra pagada.");
            return false;
        }

        if (valor <= 0) {
            System.out.println("Error: El monto pagado debe ser mayor que cero.");
            return false;
        }

        double total = factura.calcularTotal();
        if (valor < total) {
            System.out.println("Error: El monto pagado debe cubrir el total de la factura (" + total + ").");
            return false;
        }

        boolean pagoAprobado = medioPago.pagar(valor);
        if (pagoAprobado) {
            factura.setEstado(EstadoFactura.PAGADA);
            return true;
        }

        return false;
    }
}
