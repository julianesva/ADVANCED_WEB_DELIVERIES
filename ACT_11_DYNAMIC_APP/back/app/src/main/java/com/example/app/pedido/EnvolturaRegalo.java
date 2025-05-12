package com.example.app.pedido;

/**
 * Agrega la opción de envoltura de regalo a un pedido.
 * Es una clase decoradora que añade una descripción y un costo extra.
 */
public class EnvolturaRegalo extends PedidoDecorador {

    /**
     * Recibe un pedido al que se le agregará la envoltura de regalo.
     */
    public EnvolturaRegalo(Pedido pedido) {
        super(pedido);
    }

    /**
     * Devuelve la descripción del pedido incluyendo la envoltura de regalo.
     */
    public String getDescripcion() {
        return pedido.getDescripcion() + ", Envoltura de regalo";
    }

    /**
     * Devuelve el costo total del pedido con la envoltura de regalo.
     */
    public double getCosto() {
        return pedido.getCosto() + 5.0;
    }
}
