package com.example.app.pedido;

/**
 * Clase abstracta que actúa como base para los decoradores de pedidos.
 * Esta clase implementa la interfaz `Pedido` y se encarga de almacenar 
 * el objeto `Pedido` original al que se le agregarán nuevas funcionalidades.
 */
public abstract class PedidoDecorador implements Pedido {
    
    // Referencia al objeto Pedido que se está decorando
    protected Pedido pedido;

    /**
     * Constructor que recibe un objeto `Pedido` y lo almacena para su posterior decoración.
     */
    public PedidoDecorador(Pedido pedido) {
        this.pedido = pedido;
    }
}

