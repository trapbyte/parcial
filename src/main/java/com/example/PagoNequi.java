package com.example;

public class PagoNequi implements MedioPago {
    @Override
    public boolean pagar(double valor) {
        if (valor <= 0) {
            System.out.println("Pago rechazado en Nequi: El valor debe ser mayor a 0.");
            return false;
        }
        System.out.println("Pago aprobado exitosamente mediante Nequi.");
        return true;
    }
}
