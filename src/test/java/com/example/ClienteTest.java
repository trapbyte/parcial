package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testCrearClienteDatosValidos() {
        Cliente cliente = new Cliente("Sergio", "123456", "sergio@example.com");
        assertNotNull(cliente);
        assertEquals("Sergio", cliente.getNombre());
        assertEquals("123456", cliente.getTelefono());
        assertEquals("sergio@example.com", cliente.getCorreo());
    }

    @Test
    public void testCrearClienteCorreoVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente("Sergio", "123456", "");
        });
    }

    @Test
    public void testCrearClienteNombreNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cliente(null, "123456", "sergio@example.com");
        });
    }
}
