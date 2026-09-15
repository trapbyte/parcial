package com.example;

public class PagoPSE implements MedioPago {
    @Override
    public boolean pagar(double valor) {
        if (valor <= 0) {
            System.out.println("Pago rechazado en PSE: El valor debe ser mayor a 0.");
            return false;
        }
        System.out.println("Pago aprobado exitosamente mediante PSE.");
        return true;
    }
}
