package com.example;

public class PagoTarjeta implements MedioPago {
    @Override
    public boolean pagar(double valor) {
        if (valor <= 0) {
            System.out.println("Pago rechazado en Tarjeta: El valor debe ser mayor a 0.");
            return false;
        }
        System.out.println("Pago aprobado exitosamente mediante Tarjeta.");
        return true;
    }
}
