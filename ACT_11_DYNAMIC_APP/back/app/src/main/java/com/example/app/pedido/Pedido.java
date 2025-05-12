package com.example.app.pedido;

/**
 * Interfaz Pedido
 *
 * Esta interfaz define lo que debe tener un pedido: una descripción y un costo.
 * Forma parte del patrón de diseño **Decorator**.
 *
 * Todas las clases que representen un pedido, ya sea básico o con extras,
 * deben implementar esta interfaz.
 */
public interface Pedido {
    /**
     * Devuelve la descripción del pedido.
     */
    String getDescripcion();

    /**
     * Devuelve el costo total del pedido.
     */
    double getCosto();
}
