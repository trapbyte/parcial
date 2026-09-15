package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicioFacturacion servicio = new ServicioFacturacion();

        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Registrar cuenta");
            System.out.println("3. Crear factura");
            System.out.println("4. Calcular impuesto");
            System.out.println("5. Calcular total");
            System.out.println("6. Pagar factura");
            System.out.println("7. Consultar facturas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String opcionStr = scanner.nextLine();
            int opcion;
            try {
                opcion = Integer.parseInt(opcionStr);
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número válido.");
                continue;
            }

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Teléfono: ");
                        String telefono = scanner.nextLine();
                        System.out.print("Correo: ");
                        String correo = scanner.nextLine();
                        Cliente cliente = new Cliente(nombre, telefono, correo);
                        servicio.registrarCliente(cliente);
                        System.out.println("Cliente registrado exitosamente.");
                        break;
                    case 2:
                        System.out.print("Titular: ");
                        String titular = scanner.nextLine();
                        System.out.print("Tipo de cuenta (1. Ahorros, 2. Corriente): ");
                        int tipoCuenta = Integer.parseInt(scanner.nextLine());
                        System.out.print("Saldo inicial: ");
                        double saldo = Double.parseDouble(scanner.nextLine());

                        Cuenta cuenta = (tipoCuenta == 1)
                                ? new CuentaAhorro(titular, saldo)
                                : new CuentaCorriente(titular, saldo);
                        servicio.registrarCuenta(cuenta);
                        System.out.println("Cuenta registrada exitosamente.");
                        break;
                    case 3:
                        System.out.print("Número de factura: ");
                        String numero = scanner.nextLine();

                        if (servicio.getClientes().isEmpty()) {
                            System.out.println("Debe registrar al menos un cliente primero.");
                            break;
                        }

                        // En un caso real se buscaría al cliente por ID, acá tomamos el primero como
                        // simplificación
                        // o lo pedimos por consola. Para cumplir con facilidad, mostraremos los
                        // clientes.
                        System.out.println(
                                "Seleccione el cliente (índice 0 a " + (servicio.getClientes().size() - 1) + "): ");
                        for (int i = 0; i < servicio.getClientes().size(); i++) {
                            System.out.println(i + ". " + servicio.getClientes().get(i).getNombre());
                        }
                        int idxCliente = Integer.parseInt(scanner.nextLine());
                        Cliente clFactura = servicio.getClientes().get(idxCliente);

                        System.out.print("Concepto: ");
                        String concepto = scanner.nextLine();
                        System.out.print("Subtotal: ");
                        double subtotal = Double.parseDouble(scanner.nextLine());
                        System.out.print("Porcentaje de impuesto: ");
                        double impuesto = Double.parseDouble(scanner.nextLine());

                        Factura factura = new Factura(numero, clFactura, concepto, subtotal, impuesto);
                        servicio.crearFactura(factura);
                        System.out.println("Factura creada exitosamente.");
                        break;
                    case 4:
                        System.out.print("Número de factura: ");
                        String numImp = scanner.nextLine();
                        servicio.buscarFacturaPorNumero(numImp).ifPresentOrElse(
                                f -> System.out.println("El impuesto es: " + f.calcularImpuesto()),
                                () -> System.out.println("Factura no encontrada."));
                        break;
                    case 5:
                        System.out.print("Número de factura: ");
                        String numTot = scanner.nextLine();
                        servicio.buscarFacturaPorNumero(numTot).ifPresentOrElse(
                                f -> System.out.println("El total es: " + f.calcularTotal()),
                                () -> System.out.println("Factura no encontrada."));
                        break;
                    case 6:
                        System.out.print("Número de factura: ");
                        String numPago = scanner.nextLine();
                        System.out.print("Medio de pago (1. PSE, 2. Nequi, 3. Tarjeta): ");
                        int medio = Integer.parseInt(scanner.nextLine());
                        System.out.print("Monto a pagar: ");
                        double monto = Double.parseDouble(scanner.nextLine());

                        MedioPago medioPago;
                        if (medio == 1)
                            medioPago = new PagoPSE();
                        else if (medio == 2)
                            medioPago = new PagoNequi();
                        else
                            medioPago = new PagoTarjeta();

                        servicio.pagarFactura(numPago, medioPago, monto);
                        break;
                    case 7:
                        System.out.println("\n--- Lista de Facturas ---");
                        for (Factura f : servicio.getFacturas()) {
                            System.out.printf(
                                    "Num: %s | Cliente: %s | Subtotal: %.2f | Impuesto: %.2f | Total: %.2f | Estado: %s%n",
                                    f.getNumero(), f.getCliente().getNombre(), f.getSubtotal(),
                                    f.calcularImpuesto(), f.calcularTotal(), f.getEstado());
                        }
                        break;
                    case 0:
                        System.out.println("Saliendo...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error procesando la operación: " + e.getMessage());
            }
        }
    }
}