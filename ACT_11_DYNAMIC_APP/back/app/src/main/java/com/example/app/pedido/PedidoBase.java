package com.example.app.pedido;

/**
 * Representa un pedido básico sin ningún extra.
 * Es la implementación base del patrón Decorator.
 */
public class PedidoBase implements Pedido {

    /**
     * Devuelve una descripción del pedido.
     */
    public String getDescripcion() {
        return "Pedido básico";
    }

    /**
     * Devuelve el costo del pedido básico.
     */
    public double getCosto() {
        return 100.0;
    }
}
