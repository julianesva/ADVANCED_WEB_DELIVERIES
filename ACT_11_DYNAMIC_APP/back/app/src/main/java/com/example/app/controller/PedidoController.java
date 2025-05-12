package com.example.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.pedido.EnvioRapido;
import com.example.app.pedido.EnvolturaRegalo;
import com.example.app.pedido.Pedido;
import com.example.app.pedido.PedidoBase;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {

    @PostMapping("/calcular")
    public Map<String, Object> calcular(@RequestBody List<String> extras) {
        
        Pedido pedido = new PedidoBase();

        for (String extra : extras) {
            switch (extra) {
                case "envio":
                    pedido = new EnvioRapido(pedido);
                    break;
                case "regalo":
                    pedido = new EnvolturaRegalo(pedido);
                    break;
                // Puedes seguir agregando más decoradores aquí
            }
        }

        Map<String, Object> response = new HashMap<>();
        response.put("descripcion", pedido.getDescripcion());
        response.put("costo", pedido.getCosto());
        return response;
    }
}
