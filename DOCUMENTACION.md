# Documentación del Dominio: Sistema de Facturación

## Reglas del Negocio Identificadas
1. **Validación de Clientes:** Todo cliente registrado debe poseer obligatoriamente nombre, teléfono y correo electrónico.
2. **Validación de Cuentas:** Las cuentas bancarias no admiten transacciones (consignar o retirar) con valores iguales o menores a cero.
3. **Restricciones de Saldo:** Una `Cuenta de Ahorros` tiene prohibido quedar con saldo negativo. Por su parte, la `Cuenta Corriente` aplica un cobro de comisión fijo por cada transacción de retiro.
4. **Validación de Facturas:** Los montos base (subtotales) no pueden ser negativos, y los porcentajes de impuestos deben oscilar únicamente entre 0 y 100.
5. **Ciclo de Vida del Pago:** Toda factura nace en estado `PENDIENTE`.
6. **Restricción de Pagos:** Solo se procesan pagos de facturas `PENDIENTES`. El monto recibido debe ser mayor a cero, cubrir la totalidad del importe (subtotal + impuestos) y los fondos deben estar disponibles en la cuenta del cliente.
7. **Inmutabilidad del Estado:** Una factura en estado `PAGADA` cierra su ciclo y no puede volver a pagarse bajo ninguna circunstancia.

## Casos de Uso Principales

### 1. Registrar un Cliente
- **Actor:** Usuario del sistema.
- **Flujo principal:** El usuario ingresa nombre, teléfono y correo. El sistema verifica que los datos no estén vacíos y almacena el cliente en memoria para uso futuro.

### 2. Registrar una Cuenta Bancaria
- **Actor:** Usuario del sistema.
- **Flujo principal:** El usuario define el titular, tipo de cuenta (Ahorros o Corriente) y el saldo inicial. El sistema valida que el saldo inicial sea mayor o igual a cero y guarda la cuenta.

### 3. Generar una Factura
- **Actor:** Usuario del sistema.
- **Flujo principal:** El usuario asocia un cliente registrado, ingresa un concepto, subtotal e impuesto. El sistema calcula internamente el monto de impuesto y el total a pagar, y guarda la factura con estado `PENDIENTE`.

### 4. Consultar Facturas y Totales
- **Actor:** Usuario del sistema.
- **Flujo principal:** El sistema solicita un número de factura y retorna el valor de su impuesto calculado o el valor total sumado. También puede desplegar la lista general de facturas con sus respectivos estados.

### 5. Pagar una Factura
- **Actor:** Usuario del sistema.
- **Flujo principal:** El usuario indica qué factura pagar, la cuenta desde donde saldrá el dinero y el medio de pago a utilizar (PSE, Nequi o Tarjeta).
- **Flujo alternativo (Validación):** El sistema verifica que la factura exista y esté `PENDIENTE`. Luego, intenta retirar el monto exacto de la cuenta seleccionada. Si la cuenta tiene fondos, el medio de pago aprueba la transacción y la factura cambia a estado `PAGADA`. Si no hay fondos, el sistema aborta y mantiene el estado `PENDIENTE`.
