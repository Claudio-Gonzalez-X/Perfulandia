package com.perfulandia.pedidoservice.controller;

import com.perfulandia.pedidoservice.model.Pedido;
import com.perfulandia.pedidoservice.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @PostMapping
    public Pedido guardar(@RequestBody Pedido pedido) {
        return service.guardar(pedido);
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable long id) {
        service.eliminar(id);
    }
}
