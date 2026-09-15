package com.example;

public class Factura implements FacturaOperacion {
    private String numero;
    private Cliente cliente;
    private String concepto;
    private double subtotal;
    private double porcentajeImpuesto;
    private EstadoFactura estado;

    public Factura(String numero, Cliente cliente, String concepto, double subtotal, double porcentajeImpuesto) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de factura no puede estar vacío");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        if (concepto == null || concepto.trim().isEmpty()) {
            throw new IllegalArgumentException("El concepto no puede estar vacío");
        }
        if (subtotal < 0) {
            throw new IllegalArgumentException("El subtotal no puede ser negativo");
        }
        if (porcentajeImpuesto < 0 || porcentajeImpuesto > 100) {
            throw new IllegalArgumentException("El porcentaje de impuesto debe estar entre 0 y 100");
        }

        this.numero = numero;
        this.cliente = cliente;
        this.concepto = concepto;
        this.subtotal = subtotal;
        this.porcentajeImpuesto = porcentajeImpuesto;
        this.estado = EstadoFactura.PENDIENTE;
    }

    public String getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getConcepto() {
        return concepto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getPorcentajeImpuesto() {
        return porcentajeImpuesto;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }

    @Override
    public double calcularImpuesto() {
        return subtotal * (porcentajeImpuesto / 100.0);
    }

    @Override
    public double calcularTotal() {
        return subtotal + calcularImpuesto();
    }
}
