package com.example.app.pedido;

/**
 * Agrega la opción de envío rápido a un pedido.
 * Es una clase decoradora que añade una descripción y un costo extra.
 */
public class EnvioRapido extends PedidoDecorador {

    /**
     * Recibe un pedido al que se le agregará el envío rápido.
     */
    public EnvioRapido(Pedido pedido) {
        super(pedido);
    }

    /**
     * Devuelve la descripción del pedido incluyendo el envío rápido.
     */
    public String getDescripcion() {
        return pedido.getDescripcion() + ", Envío rápido";
    }

    /**
     * Devuelve el costo total del pedido con el envío rápido.
     */
    public double getCosto() {
        return pedido.getCosto() + 15.0;
    }
}
